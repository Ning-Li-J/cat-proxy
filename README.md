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
|      |                       |                              |
|      |                       |                              |
|      |                       |                              |
|      |                       |                              |



## 3、接口详情

### 3.1、查询Transaction Type列表

#### (1)、说明

+ 查询Transaction报表的所有Type信息
+ 该接口包括对过去数据的查询

#### (2)、接口

+ URL：/transaction/allType
+ Method：GET

#### (3)、请求参数

| 参数       | 类型   | 非空 | 取值                             | 备注                                                         |
| ---------- | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip         | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain     | String | 是   |                                  | 查询的应用名字                                               |
| step       | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| reportType | String | 是   | day                              | 目前只做实时模式                                             |

#### (4)、响应参数

| 参数         | 类型 | 取值 | 备注            |
| ------------ | ---- | ---- | --------------- |
| transactions | 数组 |      | 多个Transaction |

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
        "transactions": [
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
- Method：GET

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
        "transactions": [
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
- Method：GET

#### (3)、请求参数

| 参数   | 类型   | 非空 | 取值                             | 备注                                                         |
| ------ | ------ | ---- | -------------------------------- | ------------------------------------------------------------ |
| ip     | String | 是   | All,<br>xxx.xx.xx.x              | All代表所有机器                                              |
| domain | String | 是   |                                  | 查询的应用名字                                               |
| step   | int    | 否   | 范围：step <= 0<br>步长单位:小时 | 查询当前时间，不填<br>查询过去时间，填相对于当前时间步长<br>例：查询过去1小时 ，step = -1 |
| type   | String | 是   |                                  | 要获取具体信息的type                                         |

#### (4)、响应参数

| 参数                 | 类型           | 取值 | 备注                   |
| -------------------- | -------------- | ---- | ---------------------- |
| durationDistribution | TargetInfo类型 |      | 请求分布柱状图         |
| averageDurationTime  | TargetInfo类型 |      | 请求平均持续时间柱状图 |
| hitsNum              | TargetInfo类型 |      | 请求命中数量柱状图     |
| failuresNum          | TargetInfo类型 |      | 命中失败数量柱状图     |
| branchInfoList       | BranchInfo[ ]  |      | 请求在机器分布具体情况 |

- TargetInfo

| 参数   | 类型      | 取值 | 备注   |
| ------ | --------- | ---- | ------ |
| xindex | String[ ] |      | x轴值  |
| yindex | String[ ] |      | y轴值  |
| data   | Point[ ]  |      | 柱状图 |

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
        "durationDistribution": {
            "data": [
                {
                    "x": "0",
                    "y": "21"
                },
                {
                    "x": "1",
                    "y": "9"
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
            ]
        },
        "averageDurationTime": {
            "data": [
                {
                    "x": "25",
                    "y": "147"
                },
                {
                    "x": "26",
                    "y": "53.5"
                },
                ......
            ],
            "xindex": [
                "0",
                "1",
                ......
            ],
            "yindex": [
                "250",
                "200",
                .......
            ]
        },
        "hitsNum": {
            "data": [
                {
                    "x": "25",
                    "y": "3"
                },
                {
                    "x": "26",
                    "y": "6"
                },
                ......
            ],
            "xindex": [
                "0",
                "1",
                "2",
                ......
            ],
            "yindex": [
                "10",
                "8",
                ......
            ]
        },
        "failuresNum": {
            "data": [],
            "xindex": [
                "0",
                "1",
                ......
            ],
            "yindex": [
                "5",
                "4",
                ......
            ]
        },
        "branchInfoList": [
            {
                "ip": "192.168.0.111",
                "total": "55",
                "failure": "0",
                "min": "0",
                "max": "172",
                "avg": "17.6",
                "std": "40.5"
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
- Method：GET

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



