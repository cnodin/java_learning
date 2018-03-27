/**
 * @api {get} /api/v1/bandwidth/current 001-今日带宽总览
 * @apiDescription 某客户当前时间的所有带宽信息
 * @apiName bandwidth/current
 * @apiGroup Bandwidth
 *
 * @apiUse AuthHeaders
 *
 * @apiExample Example usage:
 * curl -i -H "Authorization: Bearer ${jwtValue}" http://localhost:8081/api/v1/bandwidth/current/
 *
 * @apiSuccessExample {json} Success-Response:
 *     HTTP/1.1 200 OK
 *    {
 *      "code": "000000",
 *      "data": {
 *          "totalFlow": "79.9",
 *          "totalRequest": "3157",
 *          "maxBandWidth": "0.0",
 *          "bandWidths": {
 *              "2017-12-11 00:00": 0,
 *              "2017-12-11 00:05": 0,
 *              "2017-12-11 10:05": 0
 *          }
 *      },
 *      "date": "1512958068156",
 *      "msg": "Success"
 *    }
 *
 * @apiUse CommonSuccess
 * @apiSuccess {Boolean} data.totalFlow 总流量
 * @apiSuccess {String} data.totalRequest  总请求数
 * @apiSuccess {String} data.maxBandWidth  总带宽
 * @apiSuccess {String[]} data.bandWidths  带宽明细, 时间点:带宽值
 *
 *
 */

/**
 * @api {get} /api/v1/bandwidth/detail 002-获取带宽明细
 * @apiDescription 指定客户名下域名，获取其带宽明细
 * @apiName bandwidth/detail
 * @apiGroup Bandwidth
 *
 * @apiUse AuthHeaders
 *
 * @apiParam {String} domains 域名. 如果有多个, 用逗号隔开
 * @apiParam {String} startTime 开始时间, 采用yyyyMMdd格式
 * @apiParam {String} endTime 结束时间, 采用yyyyMMdd格式
 *
 * @apiExample Example usage:
 * curl -i -H "Authorization: Bearer ${jwtValue}" http://localhost:8081/api/v1/bandwidth/detail?domains=wiki.fjsdn.com&startTime=20170101&endTime=20170102
 *
 * @apiSuccessExample {json} Success-Response:
 *      HTTP/1.1 200 OK
 *     {
 *      "code": "000000",
 *      "data": {
 *          "flows": {
 *              "2017-01-01 00:00": 0,
 *              "2017-01-02 00:00": 0
 *          },
 *          "requests": {
 *              "2017-01-01 00:00": 0,
 *              "2017-01-02 00:00": 0
 *          },
 *          "bandWidths": {
 *              "2017-01-01 00:00": 0,
 *              "2017-01-02 00:00": 0
 *          },
 *          "status": {
 *              "2XX": {
 *                  "all": {
 *                      "2017-01-01 00:00": 0,
 *                      "2017-01-02 00:00": 0
 *                  },
 *                  "hit": {
 *                      "2017-01-01 00:00": 0,
 *                      "2017-01-02 00:00": 0
 *                  },
 *                  "origin": null
 *              },
 *              "4XX": {
 *                  "all": {
 *                      "2017-01-01 00:00": 0,
 *                      "2017-01-02 00:00": 0
 *                  },
 *                  "hit": {
 *                      "2017-01-01 00:00": 0,
 *                      "2017-01-02 00:00": 0
 *                  },
 *                  "origin": null
 *              },
 *              "5XX": {
 *                  "all": {
 *                      "2017-01-01 00:00": 0,
 *                      "2017-01-02 00:00": 0
 *                  },
 *                  "hit": {
 *                      "2017-01-01 00:00": 0,
 *                      "2017-01-02 00:00": 0
 *                  },
 *                  "origin": null
 *              }
 *        }
 *      },
 *      "date": "1512959325236",
 *      "msg": "Success"
 *    }
 *
 * @apiSuccess {String[]} flows 流量明细, 时刻:流量值
 * @apiSuccess {String[]} requests  请求明细, 时刻:请求数
 * @apiSuccess {String[]} bandWidths  带宽明细, 时刻:请求数
 * @apiSuccess {Object[]} status  Http状态值
 * @apiSuccess {String[]} status.all  所有返回状态值, 时刻:数量
 * @apiSuccess {String[]} status.hit  命中次数, 时刻:次数
 * @apiSuccess {String[]} status.origin  回源次数, 时刻:次数
 */