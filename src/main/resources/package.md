```json5
{
  "order": 0,
  "version": 60001,
  "index_patterns": [
    "ps-seal-log*"
  ],
  "settings": {
    "index": {
      "number_of_shards": "5",
      "number_of_replicas": 2,
      "refresh_interval": "5s"
    }
  },
  "mappings": {
    "dynamic_templates": [{
      "message_field": {
        "path_match": "message",
        "mapping": {
          "norms": false,
          "type": "text"
        },
        "match_mapping_type": "string"
      }
    },
      {
        "string_fields": {
          "mapping": {
            "norms": false,
            "type": "text",
            "fields": {
              "keyword": {
                "ignore_above": 256,
                "type": "keyword"
              }
            }
          },
          "match_mapping_type": "string",
          "match": "*"
        }
      }
    ],
//    这里坑比较多，properties只不过是一个命名空间而已，你也可以取别的名字，比如官方喜欢用tweet
    "properties": {
      "@timestamp": {
        "type": "date",
        "format": "yyyy-MM-dd HH:mm:ss"
      },
      "es_id": {
        "type": "long"
      },
      "seal_name": {
        "type": "text"
      },
      "user_id": {
        "type": "long"
      },
      "user_name": {
        "type": "text"
      },
      "seal_dept_id": {
        "type": "long"
      },
      "seal_dept_name": {
        "type": "text"
      },
      "user_dept_id": {
        "type": "long"
      },
      "user_dept_name": {
        "type": "text"
      },
      "business_type": {
        "type": "keyword"
      },
      "action_time": {
        "type": "date",
        "format": "yyyy-MM-dd HH:mm:ss"
      },
      "es_key": {
        "type": "long"
      }
    }
  },
  "aliases": {}
}
```



{
	"order": 0,
	"version": 60001,
	"index_patterns": [
		"ps_seal_log*"
	],
	"settings": {
		"index": {
			"number_of_shards": "5",
			"number_of_replicas": 1,
			"refresh_interval": "5s"
		}
	},
	"mappings": {
			"properties": {
				"@timestamp": {
					"format": "strict_date_optional_time||epoch_millis",
          "type": "date"
				},
				"es_id": {
					"type": "long"
				},
				"seal_name": {
					"type": "keyword"
				},
				"user_id": {
					"type": "long"
				},
				"user_name": {
					"type": "text",
					"analyzer": "ik_smart"
				},
				"seal_dept_id": {
					"type": "long"
				},
				"seal_dept_name": {
					"type": "text",
					"analyzer": "ik_smart"
				},
				"user_dept_id": {
					"type": "long"
				},
				"user_dept_name": {
					"type": "text",
					"analyzer": "ik_smart"
				},
				"business_type": {
					"type": "keyword"
				},
				"actionTime": {
			  	"type": "date",
          "format": "yyyy-MM-dd HH:mm:ss"
				},
				"es_key": {
					"type": "long"
				}
			}
	},
	"aliases": {}
}