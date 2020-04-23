# cat-proxy

## 1、通用说明

### (1)、BaseRequest

#### <1>、说明

- 所有请求通用的前缀，参数为json类型或RESTful风格，具体接口处描述

#### <2>、接口

- IP：106.13.67.118
- Port：7000
- ContextPath：/cat-proxy

#### <3>、示例

```http
//所有接口的前缀，后期部署会变化
http://106.13.67.118:7000/cat-proxy
```

### (2)、BaseResponse

#### <1>、说明

- 所有的响应的基本格式，所有都遵循BaseResponse的风格
- 具体接口处只说明result的具体参数
- 格式：json

#### <2>、参数

| 参数      | 类型    | 含义                             | 备注                       |
| --------- | ------- | -------------------------------- | -------------------------- |
| success   | boolean | 此次请求是否成功<br>true / false |                            |
| errorCode | int     | 错误码                           | 具体查看错误码汇总         |
| errorMsg  | String  | 错误提示                         | 具体查看错误码汇总         |
| result    | <T>     | 响应的数据                       | 数据具体格式取决于每个接口 |

#### <3>、示例

- 成功

```json
{
    "success":true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result":xxxxx
}
```

- 失败

```json
{
	"success":false,
    "errorCode":500000,
    "errorMsg":"System error",
}
```

### 3)、ErrorCode

#### <1>、说明

系统所有错误码与含义

#### <2>、错误码

- 系统错误码

| errorCode | errorMsg             | 备注         |
| --------- | -------------------- | ------------ |
| 100000    | SUCCESS              | 成功         |
| 500000    | System error         | 系统未知异常 |
| 500001    | Request Param Error! | 请求参数错误 |
| 500002    | Cat response error!  | 请求cat失败  |

## 2、接口列表

| 编号 | 接口                  | 说明                         |
| ---- | --------------------- | ---------------------------- |
| 3.1  | /transaction/allType  | 查询Transaction Type列表     |
| 3.2  | /transaction/allName  | 查询Transaction Name列表     |
| 3.3  | /transaction/typeInfo | 查询Transaction Type具体的值 |
| 3.4  | /transaction/nameInfo | 查询Transaction Name具体的值 |
| 3.5  | /event/allType        | 查询Event Type列表           |
| 3.6  | /event/allName        | 查询Event Name列表           |
| 3.7  | /event/typeInfo       | 查询Event Type具体的值       |
| 3.8  | /event/nameInfo       | 查询Event Name具体的值       |
| 3.9  | /heart/hostHeart      | 查询Host Heart具体的值       |
| 3.10 | /host/ip              | 查询部署的机器IP             |
| 3.11 | /project/allDomain    | 查询所有项目名称             |
| 3.12 | /auth/login           | 登录                         |
| 3.13 | /auth/logout          | 登出                         |
| 3.14 | /alert/simpRule       | 查询告警规则列表             |
| 3.15 | /alert/ruleInfo       | 查询告警规则详情             |
| 3.16 | /alert/delete         | 删除告警规则                 |
| 3.17 | /alert/create         | 创建告警股则                 |
|      |                       |                              |



## 3、接口详情

### 3.1、查询Transaction Type列表

#### (1)、说明

+ 查询Transaction报表的所有Type信息
+ 该接口包括对过去数据的查询

#### (2)、接口

+ URL：/transaction/allType
+ Method：POST

#### (3)、请求参数

| 参数       | 类型   | 非空 | 取值                             | 备注                                                         |
| ---------- | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip         | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain     | String | 是   |                                  | 查询的应用名字                                               |
| step       | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| reportType | String | 是   | day                              | 目前只做实时模式                                             |

#### (4)、响应参数

| 参数            | 类型           | 取值 | 备注            |
| --------------- | -------------- | ---- | --------------- |
| transactionList | Transaction[ ] |      | 多个Transaction |

+ Transaction参数

| 参数    | 类型   | 取值 | 备注           |
| ------- | ------ | ---- | -------------- |
| name    | String |      | 具体Type的名称 |
| total   | String |      |                |
| failure | String |      |                |
| min     | String |      |                |
| max     | String |      |                |
| avg     | String |      |                |
| line95  | String |      |                |
| line999 | String |      |                |
| std     | String |      |                |
| qps     | String |      |                |

#### (5)、示例

+ 请求

```json
// 查询当前时间
{	
	"domain": "cat",
	"ip": "All",
	"reportType": "day"
}
//查询过去一小时的
{	
	"domain": "cat",
	"ip": "All",
	"reportType": "day",
	"step": -1	
}
```

+ 响应

```json
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "transactionList": [
            {
                "name": " URL.Forward",
                "total": "1",
                "failure": "0",
                "min": "135",
                "max": "135",
                "avg": "135.0",
                "line95": "135.0",
                "line999": "135.0",
                "std": "0.0",
                "qps": "0.0"
            },
            {
                "name": " URL",
                "total": "7",
                "failure": "0",
                "min": "4",
                "max": "172",
                "avg": "94.6",
                "line95": "170.0",
                "line999": "170.0",
                "std": "68.6",
                "qps": "0.0"
            }
        ]
    }
}
```

### 3.2、查询Transaction Name列表

#### (1)、说明

- 查询Transaction报表的所有Name信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/transaction/allName
- Method：POST

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                             | 备注                                                         |
| ------ | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                                  | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                                  | 具体要查寻的type                                             |

#### (4)、响应参数

+ **与 3.1 响应相同**



#### (5)、示例

- 请求

```json
// 查询当前时间
{
	"domain": "cat",
	"ip": "All",
	"type": "URL"
}
//查询过去一小时的
{
	"domain": "cat",
	"ip": "All",
	"type": "URL",
    "step": -1
}
```

- 响应

```json
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "transactionList": [
            {
                "name": " &nbsp;&nbsp;/cat/r/t",
                "total": "3",
                "failure": "0",
                "min": "54",
                "max": "172",
                "avg": "101.7",
                "line95": "170.0",
                "line999": "170.0",
                "std": "50.8",
                "qps": "0.0"
            },
			..........
        ]
    }
}
```

### 3.3、查询Transaction Type具体的值

#### (1)、说明

- 查询Transaction报表某一Type具体的信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/transaction/typeInfo
- Method：POST

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                             | 备注                                                         |
| ------ | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                                  | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                                  | 要获取具体信息的type                                         |

#### (4)、响应参数

| 参数           | 类型          | 取值 | 备注               |
| -------------- | ------------- | ---- | ------------------ |
| targetInfoList | TargetInfo[ ] |      | 请求分布柱状图     |
| branchInfoList | BranchInfo[ ] |      | 各机器间的分布情况 |

- TargetInfo

| 参数   | 类型      | 取值 | 备注       |
| ------ | --------- | ---- | ---------- |
| title  | String    |      | 图标标题   |
| xunits | String    |      | x轴单位    |
| xindex | String[ ] |      | x轴值      |
| yunits | String    |      | y轴单位    |
| yindex | String[ ] |      | y轴值      |
| data   | Point[ ]  |      | 柱状图数据 |

+ Point

| 参数 | 类型   | 取值 | 备注         |
| ---- | ------ | ---- | ------------ |
| x    | String |      | 数据 x轴下标 |
| y    | String |      | 数据 y轴下标 |

+ BranchInfo

| 参数    | 类型   | 取值 | 备注 |
| ------- | ------ | ---- | ---- |
| ip      | String |      |      |
| total   | String |      |      |
| failure | String |      |      |
| min     | String |      |      |
| max     | String |      |      |
| avg     | String |      |      |
| std     | String |      |      |

#### (5)、示例

+ 请求

```json
// 当前时间
{
	"domain": "cat",
	"ip": "All",
	"type": "URL"
}

//过去一小时
{
	"domain": "cat",
	"ip": "All",
	"type": "URL",
	"step": -1
}
```

+ 响应

```json
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "targetInfoList": [
            {
                "title": "请求持续时间分布",
                "data": [
                    {
                        "x": "0",
                        "y": "34"
                    },
                    ......
                ],
                "xindex": [
                    "0",
                    "1",
                    ......
                ],
                "yindex": [
                    "50",
                    "40",
                    ......
                ],
                "xunits": "毫秒",
                "yunits": "个数"
            },
            ......
        ],
        "branchInfoList": [
            {
                "ip": "192.168.0.111",
                "total": "78",
                "failure": "0",
                "min": "0",
                "max": "55",
                "avg": "4.7",
                "std": "7.9"
            }
        ]
    }
}
```

### 3.4、查询Transaction Name具体的值

#### (1)、说明

- 查询Transaction报表某一Type中 Name的具体信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/transaction/nameInfo
- Method：POST

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                             | 备注                                                         |
| ------ | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                                  | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                                  | 要获取具体信息的type                                         |
| name   | String | 是   |                                  | 要获取具体信息的name                                         |

#### (4)、响应参数

- **与 3.3 响应相同**

#### (5)、示例

+ 请求

```json
//当前时间
{
	"domain": "cat",
	"ip": "All",
	"type": "URL",
	"name": "/cat/r/model"
}

//过去一小时
{
	"domain": "cat",
	"ip": "All",
	"type": "URL",
	"name": "/cat/r/model",
    "step": -1
}
```

+ 响应

```json
与 3.3 响应相同
```

### 3.5、查询Event Type列表

#### (1)、说明

- 查询Event报表的所有Type信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/event/allType
- Method：POST

#### (3)、请求参数

| 参数       | 类型   | 非空 | 取值                             | 备注                                                         |
| ---------- | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip         | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain     | String | 是   |                                  | 查询的应用名字                                               |
| step       | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| reportType | String | 是   | day                              | 目前只做实时模式                                             |

#### (4)、响应参数

| 参数      | 类型     | 取值 | 备注      |
| --------- | -------- | ---- | --------- |
| eventList | Event[ ] |      | 多个Event |

- Event参数

| 参数    | 类型   | 取值 | 备注           |
| ------- | ------ | ---- | -------------- |
| name    | String |      | 具体Type的名称 |
| total   | String |      |                |
| failure | String |      |                |
| qps     | String |      |                |

#### (5)、示例

- 请求

```json
// 查询当前时间
{	
	"domain": "cat",
	"ip": "All",
	"reportType": "day"
}

//查询过去一小时的
{	
	"domain": "cat",
	"ip": "All",
	"reportType": "day",
	"step": -1
}

```

- 响应

```json
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "eventList": [
            {
                "name": "UserIp",
                "total": "138",
                "failure": "0",
                "qps": "0.0"
            },
            ......
        ]
    }
}
```

### 3.6、查询Event Name列表

#### (1)、说明

- 查询Event报表的所有Name信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/event/allName
- Method：POST

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                          | 备注                                                         |
| ------ | ------ | ---- | ----------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All, xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                               | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0 步长单位:小时 | 查询当前时间，不填 查询过去时间，填相对于当前时间步长 例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                               | 具体查询的type名称                                           |

#### (4)、响应参数

| 参数      | 类型     | 取值 | 备注      |
| --------- | -------- | ---- | --------- |
| eventList | Event[ ] |      | 多个Event |

- Event参数

| 参数    | 类型   | 取值 | 备注           |
| ------- | ------ | ---- | -------------- |
| name    | String |      | 具体Type的名称 |
| total   | String |      |                |
| failure | String |      |                |
| qps     | String |      |                |

#### (5)、示例

- 请求

```
// 查询当前时间
{
	"domain": "cat",
	"ip": "All",
	"type": "SQL.Method"
}

//查询过去一小时的
{
	"domain": "cat",
	"ip": "All",
	"type": "SQL.Method",
	"step": -1
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "eventList": [
            {
                "name": " &nbsp;&nbsp;INSERT ",
                "total": "67",
                "failure": "0",
                "qps": "0.0"
            },
            {
                "name": " &nbsp;&nbsp;SELECT ",
                "total": "1,104",
                "failure": "0",
                "qps": "0.3"
            }
        ]
    }
}
```

### 3.7、查询Event Type具体的值

#### (1)、说明

- 查询Event报表某一Type具体的信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/event/typeInfo
- Method：POST

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                             | 备注                                                         |
| ------ | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                                  | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                                  | 要获取具体信息的type                                         |

#### (4)、响应参数

| 参数           | 类型          | 取值 | 备注               |
| -------------- | ------------- | ---- | ------------------ |
| targetInfoList | TargetInfo[ ] |      | 请求分布柱状图     |
| branchInfoList | BranchInfo[ ] |      | 各机器间的分布情况 |

- TargetInfo

| 参数   | 类型      | 取值 | 备注       |
| ------ | --------- | ---- | ---------- |
| title  | String    |      | 图标标题   |
| xunits | String    |      | x轴单位    |
| xindex | String[ ] |      | x轴值      |
| yunits | String    |      | y轴单位    |
| yindex | String[ ] |      | y轴值      |
| data   | Point[ ]  |      | 柱状图数据 |

- Point

| 参数 | 类型   | 取值 | 备注         |
| ---- | ------ | ---- | ------------ |
| x    | String |      | 数据 x轴下标 |
| y    | String |      | 数据 y轴下标 |

- BranchInfo

| 参数    | 类型   | 取值 | 备注 |
| ------- | ------ | ---- | ---- |
| ip      | String |      |      |
| total   | String |      |      |
| failure | String |      |      |

#### (5)、示例

- 请求

```json
// 当前时间
{
	"domain": "cat",
	"ip": "All",
	"type": "SQL.Method"
}
//过去一小时
{
	"domain": "cat",
	"ip": "All",
	"type": "SQL.Method",
	"step": -1
}
```

- 响应

```json
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "targetInfoList": [
            {
                "title": "请求命中分布",
                "data": [
                    {
                        "x": "0",
                        "y": "21"
                    },
                    ......
                ],
                "xindex": [
                    "0",
                    "1",
                    ......
                ],
                "yindex": [
                    "100",
                    "80",
                    ......
                ],
                "xunits": "分钟",
                "yunits": "个数"
            },
            ......
        ],
        "branchInfoList": [
            {
                "ip": "192.168.0.111",
                "total": "1,171",
                "failure": "0"
            }
        ]
    }
}
```

### 3.8、查询Event Name具体的值

#### (1)、说明

- 查询Event报表某一Name具体的信息
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/event/nameInfo
- Method：POST

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                          | 备注                                                         |
| ------ | ------ | ---- | ----------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All, xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                               | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0 步长单位:小时 | 查询当前时间，不填 查询过去时间，填相对于当前时间步长 例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                               | 要获取具体信息的type                                         |
| name   | String | 是   |                               | 要获取具体信息的name                                         |

#### (4)、响应参数

| 参数           | 类型          | 取值 | 备注               |
| -------------- | ------------- | ---- | ------------------ |
| targetInfoList | TargetInfo[ ] |      | 请求分布柱状图     |
| branchInfoList | BranchInfo[ ] |      | 各机器间的分布情况 |

- TargetInfo

| 参数   | 类型      | 取值 | 备注       |
| ------ | --------- | ---- | ---------- |
| title  | String    |      | 图标标题   |
| xunits | String    |      | x轴单位    |
| xindex | String[ ] |      | x轴值      |
| yunits | String    |      | y轴单位    |
| yindex | String[ ] |      | y轴值      |
| data   | Point[ ]  |      | 柱状图数据 |

- Point

| 参数 | 类型   | 取值 | 备注         |
| ---- | ------ | ---- | ------------ |
| x    | String |      | 数据 x轴下标 |
| y    | String |      | 数据 y轴下标 |

- BranchInfo

| 参数    | 类型   | 取值 | 备注 |
| ------- | ------ | ---- | ---- |
| ip      | String |      |      |
| total   | String |      |      |
| failure | String |      |      |

#### (5)、示例

- 请求

```
// 当前时间
{
	"domain": "cat",
	"ip": "All",
	"type": "SQL.Method",
	"name": "SELECT"
}
//过去一小时
{
	"domain": "cat",
	"ip": "All",
	"type": "SQL.Method",
	"name": "SELECT",
	"step": -1
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "targetInfoList": [
            {
                "title": "请求命中分布",
                "data": [
                    {
                        "x": "0",
                        "y": "21"
                    },
                    ......
                ],
                "xindex": [
                    "0",
                    "1",
                    ......
                ],
                "yindex": [
                    "25",
                    "20",
                    ......
                ],
                "xunits": "分钟",
                "yunits": "个数"
            },
            ......
        ],
        "branchInfoList": [
            {
                "ip": "192.168.0.111",
                "total": "1,104",
                "failure": "0"
            }
        ]
    }
}
```

### 3.9、查询Host Heart具体的值

#### (1)、说明

- 机器系统的各项指标
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/heart/hostHeart
- Method：POST

#### (3)、请求参数

| 参数       | 类型   | 非空 | 取值                          | 备注                                                         |
| ---------- | ------ | ---- | ----------------------------- | ------------------------------------------------------------ |
| ip         | String | 是   | All, xxx.xx.xx.x              | All代表所有机器                                              |
| domain     | String | 是   |                               | 查询的应用名字                                               |
| step       | int    | 否   | 范围：step <= 0 步长单位:小时 | 查询当前时间，不填 查询过去时间，填相对于当前时间步长 例：查询过去1小时 ，step = -1 |
| reportType | String | 是   | day                           | 目前只做实时模式                                             |

#### (4)、响应参数

| 参数         | 类型           | 取值 | 备注       |
| ------------ | -------------- | ---- | ---------- |
| targetGroups | TargetGroup[ ] |      | 多个图表组 |

- TargetGroup

| 参数           | 类型          | 取值 | 备注           |
| -------------- | ------------- | ---- | -------------- |
| groupName      | String        |      | 该组图表的标题 |
| targetInfoList | TargetInfo[ ] |      | 多个图表       |

- TargetInfo

| 参数   | 类型      | 取值 | 备注       |
| ------ | --------- | ---- | ---------- |
| title  | String    |      | 图标标题   |
| xunits | String    |      | x轴单位    |
| xindex | String[ ] |      | x轴值      |
| yunits | String    |      | y轴单位    |
| yindex | String[ ] |      | y轴值      |
| data   | Point[ ]  |      | 柱状图数据 |

- Point

| 参数 | 类型   | 取值 | 备注         |
| ---- | ------ | ---- | ------------ |
| x    | String |      | 数据 x轴下标 |
| y    | String |      | 数据 y轴下标 |

#### (5)、示例

- 请求

```
// 当前时间
{
	"domain": "cat",
	"ip": "All",
	"reportType": "day"
}
//过去一小时
{
	"domain": "cat",
	"ip": "All",
	"reportType": "day",
	"step": -1
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "targetGroups": [
            {
                "groupName": "系统详情",
                "targetInfoList": [
                    {
                        "title": "还没想好-",
                        "data": [
                            {
                                "x": "0",
                                "y": "1.2"
                            },
                            ......
                        ],
                        "xindex": [
                            "0",
                            "1",
                            ......
                        ],
                        "yindex": [
                            "5",
                            "4",
                            ......
                        ],
                        "xunits": "分钟",
                        "yunits": "个数"
                    },
                    ......
                ]
            },
            .......
        ]
    }
}
```

### 3.10、查询项目部署机器的IP

#### (1)、说明

- 项目集群部署的ip
- 该接口包括对过去数据的查询

#### (2)、接口

- URL：/host/ip
- Method：LOGIN

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                          | 备注                                                         |
| ------ | ------ | ---- | ----------------------------- | ------------------------------------------------------------ |
| domain | String | 是   |                               | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0 步长单位:小时 | 查询当前时间，不填 查询过去时间，填相对于当前时间步长 例：查询过去1小时 ，step = -1 |

#### (4)、响应参数

| 参数   | 类型      | 取值 | 备注   |
| ------ | --------- | ---- | ------ |
| ipList | String[ ] |      | IP列表 |

#### (5)、示例

- 请求

```
// 当前时间
{
	"domain": "cat"
}
//过去一小时
{
	"domain": "cat",
	"step": -1
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "ipList": [
            "All",
            "192.168.0.111"
        ]
    }
}
```

### 3.11、查询所有项目

#### (1)、说明

- 目前已添加的所有项目

#### (2)、接口

- URL：/project/allDomain
- Method：POST

#### (3)、请求参数

| 参数 | 类型 | 非空 | 取值 | 备注 |
| ---- | ---- | ---- | ---- | ---- |
| 无   |      |      |      |      |

#### (4)、响应参数

| 参数        | 类型       | 取值 | 备注     |
| ----------- | ---------- | ---- | -------- |
| projectList | Project[ ] |      | 项目列表 |

+ Project

| 参数        | 类型   | 取值 | 备注   |
| ----------- | ------ | ---- | ------ |
| department  | String |      | 部门   |
| productLine | String |      | 产品线 |
| domain      | String |      | 项目名 |
|             |        |      |        |

#### (5)、示例

- 请求

```
// 无
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "projectList": [
            {
                "department": "xupt",
                "productLine": "irs",
                "domain": "irs-api"
            },
            {
                "department": "xupt",
                "productLine": "lining",
                "domain": "test-api"
            }
        ]
    }
}
```

### 3.12、登录

#### (1)、说明

- 用户登录

#### (2)、接口

- URL：/auth/login
- Method：POST

#### (3)、请求参数

| 参数 | 类型   | 非空 | 取值 | 备注      |
| ---- | ------ | ---- | ---- | --------- |
| uid  | String | 是   |      | 例：root  |
| pwd  | String | 是   |      | 例: admin |

#### (4)、响应参数

- 通用响应

#### (5)、示例

- 请求

```
{
	"uid": "root",
	"pwd": "admin"
}
```

- 响应

```
//成功
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": null
}

//失败
{
    "success": false,
    "errorCode": 500003,
    "errorMsg": "uid not exist",
    "result": null
}
```

### 3.13、登出

#### (1)、说明

- 用户退出登录

#### (2)、接口

- URL：/auth/logout
- Method：POST

#### (3)、请求参数

+ 无

#### (4)、响应参数

- 通用响应

#### (5)、示例

- 请求

```
// http://localhost:7000/cat-proxy/auth/logout
```

- 响应

```
//成功
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": null
}
```

### 3.14、查询告警规则列表

#### (1)、说明

- 查询配置的告警规则列表

#### (2)、接口

- URL：/alert/simpRule
- Method：POST

#### (3)、请求参数

| 参数      | 类型   | 非空 | 取值                  | 备注                                     |
| --------- | ------ | ---- | --------------------- | ---------------------------------------- |
| operation | String | 是   | transaction <br>event | 告警规则列表的类型<br>transaction或event |

#### (4)、响应参数

| 参数             | 类型            | 取值 | 备注         |
| ---------------- | --------------- | ---- | ------------ |
| ruleSimpInfoList | RuleSimpInfo[ ] |      | 告警规则列表 |

- RuleSimpInfo

| 参数    | 类型   | 取值 | 备注                       |
| ------- | ------ | ---- | -------------------------- |
| domain  | String |      | 项目名字                   |
| type    | String |      | transaction或event的type名 |
| name    | String |      | transaction或event的name名 |
| item    | String |      | 监控项                     |
| isAlert | String |      | 是否告警                   |

+ 备注

```
对于item在transaction和event时值不同
transaction： 						  event:
count		执行次数					count		执行次数
avg			响应时间					failRatio	失败率
failRatio	失败率
max			最大响应时间
```

#### (5)、示例

- 请求

```
{
	"operation": "transaction"
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "ruleSimpInfoList": [
            {
                "domain": "cat",
                "type": "URL",
                "name": "All",
                "item": "count",
                "isAlert": "是"
            },
            {
                "domain": "cat",
                "type": "URL",
                "name": "URL.Server",
                "item": "count",
                "isAlert": "是"
            }
        ]
    }
}
```

### 3.15、查询告警规则详情

#### (1)、说明

- 查询某个告警规则详情

#### (2)、接口

- URL：/alert/ruleInfo
- Method：POST

#### (3)、请求参数

| 参数      | 类型   | 非空 | 取值                                                         | 备注 |
| --------- | ------ | ---- | ------------------------------------------------------------ | ---- |
| operation | String | 是   | transaction <br/>event                                       |      |
| ruleId    | String | 是   | 列表每项值相加<br>domain;type;name;item<br>例如：cat;URL;URL.Method;count |      |

#### (4)、响应参数

| 参数          | 类型         | 取值 | 备注     |
| ------------- | ------------ | ---- | -------- |
| alertRuleList | AlertRule[ ] |      | 项目列表 |

- AlertRule

| 参数       | 类型         | 取值 | 备注         |
| ---------- | ------------ | ---- | ------------ |
| starttime  | String       |      | 规则开始时间 |
| endtime    | String       |      | 规则结束时间 |
| conditions | Condition[ ] |      |              |

- Condition

| 参数          | 类型            | 取值 | 备注     |
| ------------- | --------------- | ---- | -------- |
| minute        | number          |      | 持续时间 |
| alertType     | String          |      | 告警类型 |
| subConditions | SubCondition[ ] |      |          |

- SubCondition

| 参数 | 类型   | 取值 | 备注     |
| ---- | ------ | ---- | -------- |
| type | String |      | 规则类型 |
| text | String |      | 阈值     |

#### (5)、示例

- 请求

```
{
	"operation": "event",
	"ruleId": "cat;URL;URL.Server;count"
	
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": {
        "alertRuleList": [
            {
                "starttime": "00:00",
                "endtime": "24:00",
                "conditions": [
                    {
                        "subConditions": [
                            {
                                "type": "MinVal",
                                "text": "100"
                            },
                            {
                                "type": "MinVal",
                                "text": "100"
                            }
                        ],
                        "minute": 2,
                        "alertType": "warning"
                    },
                    {
                        "subConditions": [
                            {
                                "type": "MaxVal",
                                "text": "20"
                            }
                        ],
                        "minute": 2,
                        "alertType": "warning"
                    }
                ]
            },
            {
                "starttime": "00:00",
                "endtime": "24:00",
                "conditions": [
                    {
                        "subConditions": [
                            {
                                "type": "MaxVal",
                                "text": "100"
                            }
                        ],
                        "minute": 3,
                        "alertType": "error"
                    }
                ]
            }
        ]
    }
}
```

### 3.16、删除告警规则

#### (1)、说明

- 删除已有的告警规则

#### (2)、接口

- URL：/alert/delete
- Method：POST

#### (3)、请求参数

| 参数      | 类型   | 非空 | 取值                                                         | 备注 |
| --------- | ------ | ---- | ------------------------------------------------------------ | ---- |
| operation | String | 是   | transaction <br/>event                                       |      |
| ruleId    | String | 是   | 列表每项值相加<br>domain;type;name;item<br>例如：cat;URL;URL.Method;count |      |

#### (4)、响应参数

- 通用响应

#### (5)、示例

- 请求

```
{
	"operation": "event",
	"ruleId": "cat;URL;All;failRatio"
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": null
}
```

### 3.17、创建告警规则

#### (1)、说明

- 目前已添加的所有项目

#### (2)、接口

- URL：/alert/create
- Method：POST

#### (3)、请求参数

| 参数          | 类型         | 非空 | 取值                                                         | 备注 |
| ------------- | ------------ | ---- | ------------------------------------------------------------ | ---- |
| operation     | String       | 是   | transaction <br/>event                                       |      |
| ruleId        | String       | 是   | 列表每项值相加<br>domain;type;name;item<br>例如：cat;URL;URL.Method;count |      |
| alertRuleList | AlertRule[ ] |      |                                                              |      |

- AlertRule

| 参数       | 类型         | 取值 | 备注         |
| ---------- | ------------ | ---- | ------------ |
| starttime  | String       |      | 规则开始时间 |
| endtime    | String       |      | 规则结束时间 |
| conditions | Condition[ ] |      |              |

- Condition

| 参数          | 类型            | 取值 | 备注     |
| ------------- | --------------- | ---- | -------- |
| minute        | number          |      | 持续时间 |
| alertType     | String          |      | 告警类型 |
| subConditions | SubCondition[ ] |      |          |

- SubCondition

| 参数 | 类型   | 取值 | 备注     |
| ---- | ------ | ---- | -------- |
| type | String |      | 规则类型 |
| text | String |      | 阈值     |

#### (4)、响应参数

- 通用响应

#### (5)、示例

- 请求

```
{
	"operation": "event",
	"alertRuleList": [
		{
			"conditions":[
				{
					"sub-conditions":[
						{
							"type":"MaxVal","text":"50"
						}],
					"minute":"2",
					"alertType":"warning"
				}],
			"starttime":"00:00",
			"endtime":"24:00"
		}],
	"ruleId": "cat;URL;URL.Server;count",
	"available": true
	
}
```

- 响应

```
{
    "success": true,
    "errorCode": 100000,
    "errorMsg": "SUCCESS",
    "result": null
}
```
