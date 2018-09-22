package org.pollux.graphsqldemo;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/23
 * Time: 00:13
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
//@RequestMapping()
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {

        String schema = "type Query{hello: String} schema{query: Query}";
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().type("Query",
                    builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        ExecutionResult result = build.execute("{hello}");

        return result.getData().toString();
    }

}
