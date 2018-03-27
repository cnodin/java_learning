define({ "api": [  {    "type": "post",    "url": "/auth/v1/login",    "title": "001-用户登录",    "description": "<p>用户登录接口，此接口不需要认证</p>",    "name": "login",    "group": "Auth",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "username",            "description": "<p>用户名.</p>"          },          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "password",            "description": "<p>密码.</p>"          }        ]      },      "examples": [        {          "title": "Request-Example:",          "content": "{\n    \"username\": \"admin\",\n    \"password\": \"password\"\n}",          "type": "json"        }      ]    },    "success": {      "examples": [        {          "title": "Success-Response:",          "content": "HTTP/1.1 200 OK\n\n{\n    \"code\": \"000000\",\n    \"data\": {\n                \"accountNonExpired\": \"true\",\n                \"accountNonLocked\": \"true\",\n                \"authorities\": [\n                 {\n                     \"extraAttributes\": null,\n                     \"id\": \"1\",\n                     \"remark\": \"普通用户\",\n                     \"roleName\": \"ROLE_USER\"\n                  }],\n                  \"createdBy\": null,\n                  \"createdDate\": null,\n                  \"credentialsNonExpired\": \"true\",\n                  \"dataGroup\": \"\",\n                  \"deptId\": null,\n                  \"deptName\": \"\",\n                  \"email\": \"root@fjsdn.com\",\n                  \"extraAttributes\": null,\n                  \"id\": \"1\",\n                  \"jwtToken\": \"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzd2FuIiwic3ViIjoiYWRtaW4iLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1MTI2NDY0MDAsImV4cCI6MTUxMjY0ODIwMH0.Yr7glck1q8dxXtTd6MAjS0qFoe2gsi4ZsJBeD2qguBhic4u6Ah_p3g1dsYe293PnozAfXcjsBZaFSCJLx5iGNA\",\n                  \"lastModifiedBy\": \"\",\n                  \"lastModifiedDate\": null,\n                  \"lastPasswordResetTime\": null,\n                  \"mobile\": \"13599521191\",\n                  \"username\": \"admin\",\n                  \"version\": null\n                  },\n      \"date\": \"1512646399912\",\n      \"msg\": \"Success\"\n}",          "type": "json"        }      ],      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "Boolean",            "optional": false,            "field": "accountNonExpired",            "description": "<p>账号是否过期.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "accountNonLocked",            "description": "<p>账号是否被锁定.</p>"          }        ]      }    },    "version": "0.0.0",    "filename": "src/main/apidoc/test/ApiAuth.java",    "groupTitle": "Auth"  },  {    "type": "get",    "url": "/auth/v1/refresh",    "title": "002-刷新token",    "description": "<p>刷新token，访问需要带上Authorization头</p>",    "name": "refresh",    "group": "Auth",    "success": {      "examples": [        {          "title": "Success-Response:",          "content": " HTTP/1.1 200 OK\n{\n    \"code\": \"000000\",\n    \"data\": {\n        \"expiresIn\": \"1800\",\n        \"jwtToken\": \"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzd2FuIiwic3ViIjoiYWRtaW4iLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1MTI2NDg0ODEsImV4cCI6MTUxMjY1MDI4MX0.OHXWPNFuQNr0hOtOFuP6k4euTMF1njuR7_2nkALjpWPU3tS61I-6SqnGnMDATgmKoxOIAcF5UnfrYc0sP6J1nQ\"\n       },\n     \"date\": \"1512648481752\",\n     \"msg\": \"Success\"\n }",          "type": "json"        }      ],      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "Number",            "optional": false,            "field": "expiresIn",            "description": "<p>失效时长，单位为秒</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "jwtToken",            "description": "<p>新的jwt token</p>"          }        ]      }    },    "version": "0.0.0",    "filename": "src/main/apidoc/test/ApiAuth.java",    "groupTitle": "Auth",    "header": {      "examples": [        {          "title": "Header-Example:",          "content": "{\n  \"Authorization\": \"Bearer jwt\"\n}",          "type": "json"        }      ]    }  },  {    "type": "get",    "url": "/api/v1/bandwidth/current",    "title": "001-今日带宽总览",    "description": "<p>某客户当前时间的所有带宽信息</p>",    "name": "bandwidth_current",    "group": "Bandwidth",    "examples": [      {        "title": "Example usage:",        "content": "curl -i -H \"Authorization: Bearer ${jwtValue}\" http://localhost:8081/api/v1/bandwidth/current/",        "type": "json"      }    ],    "success": {      "examples": [        {          "title": "Success-Response:",          "content": " HTTP/1.1 200 OK\n{\n  \"code\": \"000000\",\n  \"data\": {\n      \"totalFlow\": \"79.9\",\n      \"totalRequest\": \"3157\",\n      \"maxBandWidth\": \"0.0\",\n      \"bandWidths\": {\n          \"2017-12-11 00:00\": 0,\n          \"2017-12-11 00:05\": 0,\n          \"2017-12-11 10:05\": 0\n      }\n  },\n  \"date\": \"1512958068156\",\n  \"msg\": \"Success\"\n}",          "type": "json"        }      ],      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "Boolean",            "optional": false,            "field": "data.totalFlow",            "description": "<p>总流量</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "data.totalRequest",            "description": "<p>总请求数</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "data.maxBandWidth",            "description": "<p>总带宽</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "data.bandWidths",            "description": "<p>带宽明细, 时间点:带宽值</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "code",            "description": "<p>返回码</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "msg",            "description": "<p>返回信息</p>"          },          {            "group": "Success 200",            "type": "Number",            "optional": false,            "field": "date",            "description": "<p>返回时间戳</p>"          },          {            "group": "Success 200",            "type": "Object",            "optional": false,            "field": "data",            "description": "<p>返回数据</p>"          }        ]      }    },    "version": "0.0.0",    "filename": "src/main/apidoc/test/ApiBandwidth.js",    "groupTitle": "Bandwidth",    "header": {      "examples": [        {          "title": "Header-Example:",          "content": "{\n  \"Authorization\": \"Bearer jwt\"\n}",          "type": "json"        }      ]    }  },  {    "type": "get",    "url": "/api/v1/bandwidth/detail",    "title": "002-获取带宽明细",    "description": "<p>指定客户名下域名，获取其带宽明细</p>",    "name": "bandwidth_detail",    "group": "Bandwidth",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "domains",            "description": "<p>域名. 如果有多个, 用逗号隔开</p>"          },          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "startTime",            "description": "<p>开始时间, 采用yyyyMMdd格式</p>"          },          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "endTime",            "description": "<p>结束时间, 采用yyyyMMdd格式</p>"          }        ]      }    },    "examples": [      {        "title": "Example usage:",        "content": "curl -i -H \"Authorization: Bearer ${jwtValue}\" http://localhost:8081/api/v1/bandwidth/detail?domains=wiki.fjsdn.com&startTime=20170101&endTime=20170102",        "type": "json"      }    ],    "success": {      "examples": [        {          "title": "Success-Response:",          "content": "  HTTP/1.1 200 OK\n {\n  \"code\": \"000000\",\n  \"data\": {\n      \"flows\": {\n          \"2017-01-01 00:00\": 0,\n          \"2017-01-02 00:00\": 0\n      },\n      \"requests\": {\n          \"2017-01-01 00:00\": 0,\n          \"2017-01-02 00:00\": 0\n      },\n      \"bandWidths\": {\n          \"2017-01-01 00:00\": 0,\n          \"2017-01-02 00:00\": 0\n      },\n      \"status\": {\n          \"2XX\": {\n              \"all\": {\n                  \"2017-01-01 00:00\": 0,\n                  \"2017-01-02 00:00\": 0\n              },\n              \"hit\": {\n                  \"2017-01-01 00:00\": 0,\n                  \"2017-01-02 00:00\": 0\n              },\n              \"origin\": null\n          },\n          \"4XX\": {\n              \"all\": {\n                  \"2017-01-01 00:00\": 0,\n                  \"2017-01-02 00:00\": 0\n              },\n              \"hit\": {\n                  \"2017-01-01 00:00\": 0,\n                  \"2017-01-02 00:00\": 0\n              },\n              \"origin\": null\n          },\n          \"5XX\": {\n              \"all\": {\n                  \"2017-01-01 00:00\": 0,\n                  \"2017-01-02 00:00\": 0\n              },\n              \"hit\": {\n                  \"2017-01-01 00:00\": 0,\n                  \"2017-01-02 00:00\": 0\n              },\n              \"origin\": null\n          }\n    }\n  },\n  \"date\": \"1512959325236\",\n  \"msg\": \"Success\"\n}",          "type": "json"        }      ],      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "flows",            "description": "<p>流量明细, 时刻:流量值</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "requests",            "description": "<p>请求明细, 时刻:请求数</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "bandWidths",            "description": "<p>带宽明细, 时刻:请求数</p>"          },          {            "group": "Success 200",            "type": "Object[]",            "optional": false,            "field": "status",            "description": "<p>Http状态值</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "status.all",            "description": "<p>所有返回状态值, 时刻:数量</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "status.hit",            "description": "<p>命中次数, 时刻:次数</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "status.origin",            "description": "<p>回源次数, 时刻:次数</p>"          }        ]      }    },    "version": "0.0.0",    "filename": "src/main/apidoc/test/ApiBandwidth.js",    "groupTitle": "Bandwidth",    "header": {      "examples": [        {          "title": "Header-Example:",          "content": "{\n  \"Authorization\": \"Bearer jwt\"\n}",          "type": "json"        }      ]    }  },  {    "type": "get",    "url": "/user/:id",    "title": "Read data of a User",    "version": "0.3.0",    "name": "GetUser",    "group": "User",    "permission": [      {        "name": "admin",        "title": "Admin access rights needed.",        "description": "<p>Optionally you can write here further Informations about the permission.</p> <p>An &quot;apiDefinePermission&quot;-block can have an &quot;apiVersion&quot;, so you can attach the block to a specific version.</p>"      }    ],    "description": "<p>Compare Verison 0.3.0 with 0.2.0 and you will see the green markers with new items in version 0.3.0 and red markers with removed items since 0.2.0.</p>",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "Number",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          }        ]      }    },    "examples": [      {        "title": "Example usage:",        "content": "curl -i http://localhost/user/4711",        "type": "json"      }    ],    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "Number",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          },          {            "group": "Success 200",            "type": "Date",            "optional": false,            "field": "registered",            "description": "<p>Registration Date.</p>"          },          {            "group": "Success 200",            "type": "Date",            "optional": false,            "field": "name",            "description": "<p>Fullname of the User.</p>"          },          {            "group": "Success 200",            "type": "String[]",            "optional": false,            "field": "nicknames",            "description": "<p>List of Users nicknames (Array of Strings).</p>"          },          {            "group": "Success 200",            "type": "Object",            "optional": false,            "field": "profile",            "description": "<p>Profile data (example for an Object)</p>"          },          {            "group": "Success 200",            "type": "Number",            "optional": false,            "field": "profile.age",            "description": "<p>Users age.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "profile.image",            "description": "<p>Avatar-Image.</p>"          },          {            "group": "Success 200",            "type": "Object[]",            "optional": false,            "field": "options",            "description": "<p>List of Users options (Array of Objects).</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "options.name",            "description": "<p>Option Name.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "options.value",            "description": "<p>Option Value.</p>"          }        ]      }    },    "error": {      "fields": {        "Error 4xx": [          {            "group": "Error 4xx",            "optional": false,            "field": "NoAccessRight",            "description": "<p>Only authenticated Admins can access the data.</p>"          },          {            "group": "Error 4xx",            "optional": false,            "field": "UserNotFound",            "description": "<p>The <code>id</code> of the User was not found.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 401 Not Authenticated\n{\n  \"error\": \"NoAccessRight\"\n}",          "type": "json"        }      ]    },    "filename": "src/main/apidoc/test/example.js",    "groupTitle": "User"  },  {    "type": "get",    "url": "/user/:id",    "title": "Read data of a User",    "version": "0.2.0",    "name": "GetUser",    "group": "User",    "permission": [      {        "name": "admin",        "title": "This title is visible in version 0.1.0 and 0.2.0",        "description": ""      }    ],    "description": "<p>Here you can describe the function. Multilines are possible.</p>",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          }        ]      }    },    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          },          {            "group": "Success 200",            "type": "Date",            "optional": false,            "field": "name",            "description": "<p>Fullname of the User.</p>"          }        ]      }    },    "error": {      "fields": {        "Error 4xx": [          {            "group": "Error 4xx",            "optional": false,            "field": "UserNotFound",            "description": "<p>The <code>id</code> of the User was not found.</p>"          }        ]      }    },    "filename": "src/main/apidoc/test/_apidoc.js",    "groupTitle": "User"  },  {    "type": "get",    "url": "/user/:id",    "title": "Read data of a User",    "version": "0.1.0",    "name": "GetUser",    "group": "User",    "permission": [      {        "name": "admin",        "title": "This title is visible in version 0.1.0 and 0.2.0",        "description": ""      }    ],    "description": "<p>Here you can describe the function. Multilines are possible.</p>",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          }        ]      }    },    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          },          {            "group": "Success 200",            "type": "Date",            "optional": false,            "field": "name",            "description": "<p>Fullname of the User.</p>"          }        ]      }    },    "error": {      "fields": {        "Error 4xx": [          {            "group": "Error 4xx",            "optional": false,            "field": "UserNotFound",            "description": "<p>The error description text in version 0.1.0.</p>"          }        ]      }    },    "filename": "src/main/apidoc/test/_apidoc.js",    "groupTitle": "User"  },  {    "type": "post",    "url": "/user",    "title": "Create a new User",    "version": "0.3.0",    "name": "PostUser",    "group": "User",    "permission": [      {        "name": "none"      }    ],    "description": "<p>In this case &quot;apiErrorStructure&quot; is defined and used. Define blocks with params that will be used in several functions, so you dont have to rewrite them.</p>",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "name",            "description": "<p>Name of the User.</p>"          }        ]      }    },    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "Number",            "optional": false,            "field": "id",            "description": "<p>The new Users-ID.</p>"          }        ]      }    },    "filename": "src/main/apidoc/test/example.js",    "groupTitle": "User",    "error": {      "fields": {        "Error 4xx": [          {            "group": "Error 4xx",            "optional": false,            "field": "NoAccessRight",            "description": "<p>Only authenticated Admins can access the data.</p>"          },          {            "group": "Error 4xx",            "optional": false,            "field": "UserNameTooShort",            "description": "<p>Minimum of 5 characters required.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 400 Bad Request\n{\n  \"error\": \"UserNameTooShort\"\n}",          "type": "json"        }      ]    }  },  {    "type": "post",    "url": "/user",    "title": "Create a User",    "version": "0.2.0",    "name": "PostUser",    "group": "User",    "permission": [      {        "name": "none"      }    ],    "description": "<p>In this case &quot;apiErrorStructure&quot; is defined and used. Define blocks with params that will be used in several functions, so you dont have to rewrite them.</p>",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "name",            "description": "<p>Name of the User.</p>"          }        ]      }    },    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "id",            "description": "<p>The Users-ID.</p>"          }        ]      }    },    "filename": "src/main/apidoc/test/_apidoc.js",    "groupTitle": "User",    "error": {      "fields": {        "Error 4xx": [          {            "group": "Error 4xx",            "optional": false,            "field": "NoAccessRight",            "description": "<p>Only authenticated Admins can access the data.</p>"          },          {            "group": "Error 4xx",            "optional": false,            "field": "UserNameTooShort",            "description": "<p>Minimum of 5 characters required.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 400 Bad Request\n{\n  \"error\": \"UserNameTooShort\"\n}",          "type": "json"        }      ]    }  },  {    "type": "put",    "url": "/user/:id",    "title": "Change a User",    "version": "0.3.0",    "name": "PutUser",    "group": "User",    "permission": [      {        "name": "none"      }    ],    "description": "<p>This function has same errors like POST /user, but errors not defined again, they were included with &quot;apiErrorStructure&quot;</p>",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "name",            "description": "<p>Name of the User.</p>"          }        ]      }    },    "filename": "src/main/apidoc/test/example.js",    "groupTitle": "User",    "error": {      "fields": {        "Error 4xx": [          {            "group": "Error 4xx",            "optional": false,            "field": "NoAccessRight",            "description": "<p>Only authenticated Admins can access the data.</p>"          },          {            "group": "Error 4xx",            "optional": false,            "field": "UserNameTooShort",            "description": "<p>Minimum of 5 characters required.</p>"          }        ]      },      "examples": [        {          "title": "Response (example):",          "content": "HTTP/1.1 400 Bad Request\n{\n  \"error\": \"UserNameTooShort\"\n}",          "type": "json"        }      ]    }  }] });
