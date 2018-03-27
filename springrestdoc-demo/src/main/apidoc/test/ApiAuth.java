/**
 * @api {post} /auth/v1/login 001-用户登录
 * @apiDescription 用户登录接口，此接口不需要认证
 * @apiName login
 * @apiGroup Auth
 *
 * @apiParam {String} username 用户名.
 * @apiParam {String} password 密码.
 *
 * @apiParamExample {json} Request-Example:
 *      {
 *          "username": "admin",
 *          "password": "password"
 *      }
 *
 * @apiSuccessExample {json} Success-Response:
 *     HTTP/1.1 200 OK
 *
 *     {
 *         "code": "000000",
 *         "data": {
 *                     "accountNonExpired": "true",
 *                     "accountNonLocked": "true",
 *                     "authorities": [
 *                      {
 *                          "extraAttributes": null,
 *                          "id": "1",
 *                          "remark": "普通用户",
 *                          "roleName": "ROLE_USER"
 *                       }],
 *                       "createdBy": null,
 *                       "createdDate": null,
 *                       "credentialsNonExpired": "true",
 *                       "dataGroup": "",
 *                       "deptId": null,
 *                       "deptName": "",
 *                       "email": "root@fjsdn.com",
 *                       "extraAttributes": null,
 *                       "id": "1",
 *                       "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzd2FuIiwic3ViIjoiYWRtaW4iLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1MTI2NDY0MDAsImV4cCI6MTUxMjY0ODIwMH0.Yr7glck1q8dxXtTd6MAjS0qFoe2gsi4ZsJBeD2qguBhic4u6Ah_p3g1dsYe293PnozAfXcjsBZaFSCJLx5iGNA",
 *                       "lastModifiedBy": "",
 *                       "lastModifiedDate": null,
 *                       "lastPasswordResetTime": null,
 *                       "mobile": "13599521191",
 *                       "username": "admin",
 *                       "version": null
 *                       },
 *           "date": "1512646399912",
 *           "msg": "Success"
 *     }
 *
 * @apiSuccess {Boolean} accountNonExpired 账号是否过期.
 * @apiSuccess {String} accountNonLocked  账号是否被锁定.
 */

/**
 * @api {get} /auth/v1/refresh 002-刷新token
 * @apiDescription 刷新token，访问需要带上Authorization头
 * @apiName refresh
 * @apiGroup Auth
 *
 * @apiUse AuthHeaders
 *
 * @apiSuccessExample {json} Success-Response:
 *      HTTP/1.1 200 OK
 *     {
 *         "code": "000000",
 *         "data": {
 *             "expiresIn": "1800",
 *             "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzd2FuIiwic3ViIjoiYWRtaW4iLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1MTI2NDg0ODEsImV4cCI6MTUxMjY1MDI4MX0.OHXWPNFuQNr0hOtOFuP6k4euTMF1njuR7_2nkALjpWPU3tS61I-6SqnGnMDATgmKoxOIAcF5UnfrYc0sP6J1nQ"
 *            },
 *          "date": "1512648481752",
 *          "msg": "Success"
 *      }
 *
 * @apiSuccess {Number} expiresIn 失效时长，单位为秒
 * @apiSuccess {String} jwtToken  新的jwt token
 */