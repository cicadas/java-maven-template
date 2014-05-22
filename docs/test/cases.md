#Test Cases

********************************************************************************


1. Line state manager for kindred action update interface

    * It should return http code 200 after insert completed

1. Line state manager action update interface

    * It should found the insert record data in db
    * It should return http code 200 after update completed
    * It update action should have the same values in update action
    * And Delete all test data
    * And should delete test record in database

1. Aex segment read interface

    * It should return http code 200 after read completed

1. bow segment(active) read interface

    * And should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for bow part

1. AEX segment creation interface
    * Reference data:  `{"name":"Yahoo/Audience Expansion/US/TestSegmentfb80dce80c15","expiration":"2013-12-31","type":"aex","description":"This is a test segment.","created_by":"liufan","exchanges":[{"name":"rmx","segment_owner":"19714","segment_seat":"23351","status":"active"}],"segment_definition":{"expansion_metadata":[{"type":"bow_segment","value":[{"segment_id":"20000608","pixel_id":"2347607"},{"segment_id":"20000609","pixel_id":"2347608"}]},{"type":"cookie","value":[{"cookie":"00vuh798h2nsi","date":"20130130","label":"pos","id":"","type":"bcookie"},{"cookie":"fjtvao98fbtog","date":"20130130","label":"pos","id":"","type":"bcookie"}]},{"type":"rmx_conversion_pixel","value":[123,456,7890]},{"type":"rmx_segment_pixel","value":[123,456,789,101112]},{"type":"gd_click_event","value":["123_456","456_789"]}]}}` 
    * It should return http code 200 and segment id after completed the creation process    * Reference data:  `{"id":"20041802"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should add metadata into cat_aex_metadata

1. aex segment update interface

    * It should return http code 200 after update completed
    * It should return http code 200 after update completed
    * When tried to read the segment from database
    * Then should have same values as last created/updated action for Head part
    * When tried to toLong the segment from database
    * And should have same values as last created/updated action for bow part

1. Bow segment(active) creation interface
    * Reference data:  `{"name":"Yahoo/BOW Retargeting/US/Standard/TestSegmente07099f2ec80","expiration":"2013-12-31","type":"bow","description":"Standard Segment Creation with RMX and APT exchange","created_by":"Targeting API Test","segment_definition":{"segdef":{"featuredef":{"featureName":"email","attrName":"domain","operator":"IN","value":["dopasowani.us","neriuminternational.com"]},"featuredefprop":[{"name":"freq","op":">=","value":1},{"name":"rec","op":"=","value":90}]}}}` 
    * It should return http code 200 and segment id after completed the creation process
    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for bow part

1. Bow segment(active) creation interface with Exchange
    * Reference data:  `{"name":"Yahoo/BOW Retargeting/US/Standard/TestSegment534f87adeeea","expiration":"2013-12-31","type":"bow","description":"Standard Segment Creation with RMX and APT exchange","created_by":"Targeting API Test","segment_definition":{"segdef":{"featuredef":{"featureName":"email","attrName":"domain","operator":"IN","value":["dopasowani.us","neriuminternational.com"]},"featuredefprop":[{"name":"freq","op":">=","value":1},{"name":"rec","op":"=","value":90}]}}}` 
    * It should return http code 200 and segment id after completed the creation process

1. Advertisers Creation creation interface
    * Reference data:  `/catsysetlAdvertiser/advertiserlist_create.json` 
    * When Before creation
    * Given 3 CatsysETL advertiser definition, two of them have same creativeID
    * It should return http code 200 after completed the creation process    * Reference data:  `{"id":"20041804"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY    * Reference data:  `{"advertiserAttrInsertCount":1,"advertiserAttrUpdateCount":1}` 
    * It should return insert count and update count
    * It should insert one advertiser into CAT_ADVERTISER_ATTR
    * It should update one advertiser into CAT_ADVERTISER_ATTR
    * It should merge property related info into cat_segment_properties

1. Creatives creation interface
    * Reference data:  `/catsysetlCreative/creativelist_create.json` 
    * When Before creation
    * Given 2 CatsysETL creatives definition
    * It should return http code 200 after completed the creation process
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Bow segment(inactive) read interface
    * Reference data:  `{"id":"20041805"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should insert apt in database
    * It should return http code 200 after read completed    * Reference data:  `{"impressionInsertCount":1,"impressionUpdateCount":1,"adAttrInsertCount":1,"adAttrUpdateCount":1,"countryInsertCount":1,"countryUpdateCount":1,"categoryInsertCount":0,"autoPoolInsertCount":1,"poolInsertCount":0}` 
    * It should return insert count and update count
    * It should insert one advertiser into CAT_AD_IMPRESSION
    * It should update one advertiser into CAT_AD_IMPRESSION
    * And should insert one advertiser into CAT_AD_ATTR
    * And should update one advertiser into CAT_AD_ATTR
    * And should insert one advertiser into CAT_AD_COUNTRY
    * It should insert rmx in database
    * And should update one advertiser into CAT_AD_COUNTRY

1. Creatives auto pool creation interface
    * Reference data:  `/catsysetlCreative/creativelist_create_autopool.json` 
    * When Before creation
    * Given 5 CatsysETL creative definition
    * It should return http code 200 after completed the creation process
    * And should have same values as last created/updated action for Head part
    * And should merge property related info into cat_segment_properties

1. Bow segment(active) deletion interface

    * And should have same values as last created/updated action for bow part

1. bow segment(inactive) read interface

    * It should return http code 200 after update completed    * Reference data:  `{"impressionInsertCount":5,"impressionUpdateCount":0,"adAttrInsertCount":5,"adAttrUpdateCount":0,"countryInsertCount":5,"countryUpdateCount":0,"categoryInsertCount":0,"autoPoolInsertCount":5,"poolInsertCount":0}` 
    * It should return insert count and update count
    * It one record should be inserted in CAT_AD_POOL
    * It others should be updates inserted in CAT_AD_AUTO_POOL    * Reference data:  `{"id":"20041808"}` 
    * It should return http code 200 after remove a segment from system
    * When tried to read the segment from database
    * Then should have same values as last created/updated action for Head part

1. Bow segment(active) read interface


1. Creatives category creation interface
    * Reference data:  `/catsysetlCreative/creativelist_create_category.json` 
    * When Before creation
    * Given 2 CatsysETL creative definition with the same creativeID
    * It should return http code 200 after completed the creation process
    * And should have same values as last created/updated action for bow part

1. Bow segment(inactive) deletion interface

    * It should return http code 200 after remove a segment from system    * Reference data:  `{"impressionInsertCount":1,"impressionUpdateCount":0,"adAttrInsertCount":1,"adAttrUpdateCount":0,"countryInsertCount":1,"countryUpdateCount":0,"categoryInsertCount":1,"autoPoolInsertCount":0,"poolInsertCount":0}` 
    * It should return insert count and update count
    * It only one record should be inserted in CAT_AD_CATEGORY

1. Creatives pool creation interface
    * Reference data:  `/catsysetlCreative/creativelist_create_pool.json` 
    * When Before creation
    * Given 2 CatsysETL creatives definition
    * It should return http code 200 after completed the creation process
    * It should return http code 200 after read completed
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * When try to read the segment after deletion
    * It should insert apt in datebase    * Reference data:  `{"impressionInsertCount":1,"impressionUpdateCount":0,"adAttrInsertCount":1,"adAttrUpdateCount":0,"countryInsertCount":1,"countryUpdateCount":0,"categoryInsertCount":0,"autoPoolInsertCount":0,"poolInsertCount":1}` 
    * It should return insert count and update count
    * It two record should be inserted in CAT_AD_POOL
    * It should insert rmx in datebase
    * Then we should toLong error code and http code 404

1. Custom segment(active) creation interface
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment8f64edcbe845","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process
    * And segment active set to 9 in CAT_MASTER_TAX0

1. Bow segment(active) read interface

    * And should have same values as last created/updated action for bow part

1. bow segment(active) read interface
    * Reference data:  `{"id":"20041811"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should merge property related info into cat_segment_properties
    * It should return http code 200 after read completed
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with duplicate name
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmente82c5de27fac","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after the creation process
    * It should return http code 409 after the creation process    * Reference data:  `{"id":"20041814"}` 

1. Custom segment(active) creation interface with members
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","originator":"zhouzh","members":["wlei","dlzhu","taoluo"],"expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment1dee4bc13c43","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process
    * And should have same values as last created/updated action for Head part
    * It should return http code 200 after update completed
    * And should have same values as last created/updated action for bow part

1. Bow segment(active) deletion interface
    * Reference data:  `{"id":"20041816"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should merge property related info into cat_segment_properties
    * It should return http code 200 after remove a segment from system
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with different created by and originator
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"zhong","originator":"qianli","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmenta1b4d271da3f","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process    * Reference data:  `{"id":"20041818"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should merge property related info into cat_segment_properties
    * When try to read the segment after deletion
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with duplicate members
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","originator":"zhouzh","members":["wlei","dlzhu","taoluo","dlzhu","dlzhu","dlzhu","dlzhu"],"expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentb4c5713dc8b2","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process
    * Then we should toLong error code and http code 404
    * When tried to toLong the segment from database
    * Then should have same values as last created/updated action for Head part    * Reference data:  `{"id":"20041819"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * And segment active set to 9 in CAT_MASTER_TAX0

1. APT Exchange Creation

    * It should merge property related info into cat_segment_properties
    * And should have same values as last created/updated action for bow part

1. bow segment(active) read interface

    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with empty created_by
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment48c2981ac97f","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment7bb5d1f38aa9","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process    * Reference data:  `null` 

1. Custom segment(active) creation interface with empty members
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","originator":"zhouzh","members":[""],"expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment0497c14623f6","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process    * Reference data:  `{"id":"20041822"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * It should merge property related info into cat_segment_properties
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with empty originator
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment156f610f4137","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process

1. APT Exchange Creation

    * It should return http code 200 after update completed

1. Custom segment(active) creation interface with null created_by
    * Reference data:  `{"type":"custom","description":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentf1dbe5202673","created_by":null,"segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process    * Reference data:  `null` 

1. Custom segment(active) creation interface with null members
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","originator":"zhouzh","members":null,"expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment7024189ef441","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment7d91e46d224c","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process    * Reference data:  `{"id":"20041824"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should merge property related info into cat_segment_properties
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with null originator
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","originator":null,"members":["wlei","dlzhu","taoluo"],"expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment8f056c9de327","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY    * Reference data:  `{"id":"20041825"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It should merge property related info into cat_segment_properties

1. APT Exchange Creation
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment460af5bfc7bc","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface with partial member list
    * Reference data:  `{"type":"custom","description":"Targeting API Test","originator":"zhouzh","created_by":"Targeting API Test","members":["wlei","dlzhu","taoluo","","zhouzh"],"expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentcda01a3130ba","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process
    * When tried to toLong the segment from database
    * Then should have same values as last created/updated action for Head part    * Reference data:  `{"id":"20041827"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * And should have same values as last created/updated action for bow part
    * It should update apt to inactive
    * It should merge property related info into cat_segment_properties
    * It should update rmx to inactive

1. Feature type creation interface
    * Reference data:  `[    {        "featureProperties": [            {                "name": "freq",                "operators": [                    "<",                    "<=",                    "=",                    ">=",                    ">"                ],                "valueType": "STRING"            },            {                "name": "rec",                "operators": [                    "="                ],                "valueType": "STRING"            }        ],        "attributes": [            {                "attributeName": "FeatureAttr1",                "supportedOperators": [                    "IN",                    "NOTIN"                ],                "valueType": "STRING"            },            {                "attributeName": "FeatureAttr2",                "supportedOperators": [                    "IN",                    "NOTIN"                ],                "valueType": "SET"            }        ],        "featureType": "f6ba4bb6fbf31",        "urSourceType": "tenantid001.features.temp",        "scoreHost": "GRID",        "subSegmentGeneration": true,        "supportedRecencies": [            15,            30,            60,            90        ],        "maxRecency": 90    },    {        "featureProperties": [            {                "name": "freq",                "operators": [                    "<",                    "<=",                    "=",                    ">=",                    ">"                ],                "valueType": "STRING"            },            {                "name": "rec",                "operators": [                    "="                ],                "valueType": "STRING"            }        ],        "attributes": [            {                "attributeName": "FeatureAttr1",                "supportedOperators": [                    "IN",                    "NOTIN"                ],                "valueType": "STRING"            },            {                "attributeName": "FeatureAttr2",                "supportedOperators": [                    "IN",                    "NOTIN"                ],                "valueType": "SET"            }        ],        "featureType": "f6ba4bb6fbf32",        "urSourceType": "tenantid001.features.temp",        "scoreHost": "GRID",        "subSegmentGeneration": true,        "supportedRecencies": [        ],        "maxRecency": 30    }]` 
    * It should return http code 204 after creation process completes

1. APT Exchange Creation

    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) creation interface
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentc61c83940a46","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 and segment id after completed the creation process

1. InstantReach with invalid segment defintion.
    * Reference data:  `{    "segdef": {        "name": "invalid-bow-segment"    }}` 
    * It should return http code 200 and the number of segDef is -1 after completed the creation process    * Reference data:  `{"segmentReach":"-1","errorCode":"1","errorMessage":"Invalid BOW segment definition"}` 

1. InstantReach with valid segment defintion.
    * Reference data:  `{    "segdef": {        "operator": "OR",        "left": {            "featuredef": {                "featureName": "socialprofile",                "attrName": "social_likes",                "operator": "IN",                "value": [                    133274596696966, 21435141328, 119229841424702, 238741262862424, 122339837820811, 155017711176141, 131065603635387, 211529678868384, 157114130996555, 103521869733800, 140925765947731, 147340521994698, 120531307994649, 357217012604, 389460654440594, 287797664611260, 120021118072351, 59602193669, 147755520528, 129335207158235, 184075834953238, 265758303445481, 174237392610284, 352723179560, 337904429712, 384784158600, 111682388861936, 156973304325610, 30246623989, 431891506856669, 157372590982731, 48610120812, 158069844251588, 119902014688632, 362751143793991, 159043010803248, 216854188337846, 146506968741658, 103860772980082, 108001189228013, 112487212099926, 184963884870028, 25063267394, 300020330047372, 122728777784601, 264066510288723, 294518136701, 151452581533713, 161820020320, 197367853716614, 112002122158937, 251690448640, 278419694419, 183480345033569, 186027968082477, 197570560308867, 111278555549100, 29627613982, 211910068848602, 167431696649905, 135841889784801, 181539535256555, 253277938029857, 202026671387, 149348595125773, 105943482769722, 107571552606256, 301551340964, 344549858967314, 252065921500382, 100688223345563, 112011912151808, 103068296408086, 214615415224988, 154025004681538, 293434814028885, 174757254886, 161179947227222                ]            }        },        "right": {            "featuredef": {                "featureName": "socialprofile",                "attrName": "bt_category",                "operator": "IN",                "value": ["244"]            }        }    }}` 
    * It should return http code 200 and number of segDef after completed the creation process    * Reference data:  `{"segmentReach":"1","errorCode":"0"}` 

1. Test Report Generate API

    * It should generate daily report & monthly report    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmente48e7043a030","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process

1. Test Advertiser Search API

    * It should return HTTP code 200 and Advertiser object containing 2 advertisers and a totalCount after the process finished    * Reference data:  `{"id":"20041829"}` 
    * It should insert segment basic info into CAT_MASTER_TAXO_PROXY

1. Test Advertiser Search API

    * It should return HTTP code 200 and Advertiser object containing empty advertisers and a totalCount after the process finished

1. Test Update Segment

    * It Should update successfully
    * It should merge property related info into cat_segment_properties

1. Test Query Segment

    * It Should Return Correct Results
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * And CAT_SYSTEM_SUPPORT_PROXY table should have default value

1. Custom segment(active) deletion interface


1. Test Report Generate API

    * It Should Scan sucessfully

1. APT Exchange Creation

    * It should return http code 200 after remove a segment from system    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentb251e96bab88","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * When try to read the segment after deletion

1. APT Exchange Creation

    * Then we should toLong error code and http code 404    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentc9caaa9a6aa3","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process
    * And segment active set to 9 in CAT_MASTER_TAX0

1. Custom segment(inactive) deletion interface


1. Segment list interface

    * It should return http code 200 after completed the creation process
    * It should return http code 200 after remove a segment from system
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * It should return segment list(with rmx/apt id)

1. Segment list interface

    * It should return http code 200 after completed the creation process

1. APT Exchange Creation
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment071d2334a7c7","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process
    * When try to read the segment after deletion
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * Then we should toLong error code and http code 404
    * And segment active set to 9 in CAT_MASTER_TAX0

1. Custom segment(active) read interface


1. RMX Exchange Creation

    * It should return http code 200 after read completed    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentd67b49c569ef","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 200 after complete the creation process
    * And should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment(inactive) read interface

    * It should return a JSON string include segment id and RMX exchange info
    * It should insert rmx exchange basic info into RMX_CATEGORY_MAPPING, and update CAT_SYSTEM_SUPPORT_PROXY, 
    * It should return http code 200 after read completed

1. APT Exchange Creation while segment is inactive

    * And should have same values as last created/updated action for Head part    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment644e59d811fb","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"},"status":"inactive"}` 
    * It should return http code 400 after complete the creation process
    * And should have same values as last created/updated action for Custom part

1. Read a segment which have members


1. Existed APT Exchange creation

    * It should return http code 200 after read completed
    * And should have same values as last created/updated action for Head part

1. Read a segment with different originator and created by user
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment4fe8443a52f5","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}` 
    * It should return http code 409 after complete the recreation process
    * It should return http code 200 after read completed

1. APT Exchange Deletion

    * And should have same values as last created/updated action for Head part

1. Read a segment which have both members and originator

    * It should return http code 200 after read completed    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment4632e4ccc00d","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_seat": "26053288866",    "status": "active"}` 
    * It should return http code 200 after complete the deletion process
    * It should update CAT_SYSTEM_SUPPORT_PROXY, CAT_MASTER_TAXO_PROXY and ACT_ACTIVE_SEGMENT_PROXY
    * And should have same values as last created/updated action for Head part

1. Read a segment which do not have members

    * It should return http code 200 after read completed
    * When try to read the APT exchange after deletion
    * Then we should toLong 404 response code
    * And should have same values as last created/updated action for Head part

1. Custom segment read interface

    * And we should toLong 404 response code
    * It should return http code 200 after update completed
    * When merge updating Json with creation Json
    * And recreate an active apt exchange

1. RMX Exchange Deletion

    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface

    * It should return http code 200 after update completed
    * When merge updating Json with creation Json    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment5a6696d26e9e","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_owner": "371178",    "segment_seat": "371178",    "segment_shared_seats": ["39397", "97746", "184243", "122580", "131852", "479544"],    "status": "active"}` 
    * It should return http code 200 after complete the deletion process
    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface

    * It should update CAT_SYSTEM_SUPPORT_PROXY and RMX_CATEGORY_MAPPING_PROXY
    * It should return http code 200 after update completed
    * When try to read the RMX exchange after deletion
    * Then we should return 404 response code
    * When merge updating Json with creation Json
    * And we should return 404 response code

1. Apx Exchange Reading

    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface

    * It should return http code 200 after update completed    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment145525091ee7","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_seat": "26053288866",    "status": "active"}` 
    * It should return http code 200 after the reading process
    * It should return a Json string contains correct apt exchange info
    * It should return http code 200 after the reading process
    * It should return a Json string contains correct apt exchange info

1. Rmx Exchange Reading

    * When merge updating Json with creation Json
    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentf3f644bec1c9","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_owner": "371178",    "segment_seat": "371178",    "segment_shared_seats": ["39397", "97746", "184243", "122580", "131852", "479544"],    "status": "active"}` 
    * It should return http code 200 after the reading process
    * It should return http code 200 after update completed
    * It should return a Json string contains correct rmx exchange info
    * It should return http code 200 after the reading process
    * When merge updating Json with creation Json
    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface

    * It should return a Json string contains correct rmx exchange info

1. APT Exchange Deletion

    * It should return http code 200 after update completed
    * When merge updating Json with creation Json
    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface

    * It should return http code 200 after update completed    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment6cbe9f484853","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_seat": "26053288866",    "status": "active"}` 
    * It should return http code 200 after complete the creation process
    * When merge updating Json with creation Json
    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Custom segment read interface

    * It should return http code 200 after update completed
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY
    * When merge updating Json with creation Json

1. APT Exchange Deletion

    * Then should have same values as last created/updated action for Head part
    * And should have same values as last created/updated action for Custom part

1. Test Report Techno Search API

    * It Should search sucessfully

1. Feature type get all interface
    * Reference data:  `` 
    * It should return http code 200 after get process completes    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentcdfb29b2ac2d","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_owner": "371178",    "segment_seat": "371178",    "segment_shared_seats": ["39397", "97746", "184243", "122580", "131852", "479544"],    "status": "active"}` 
    * It should return http code 200 after complete the creation process
    * It CAT_MASTER_TAXO.APT_SEAT_ID is the mapping of segment_seat from apt definition, while CATEGORY_ID is given in request
    * It should insert apt exchange basic into ACT_ACTIVE_SEGMENT, and update CAT_SYSTEM_SUPPORT_PROXY

1. Feature type get specific interface
    * Reference data:  `f6ba4bb6fbf32,f6ba4bb6fbf31,
1865,1864,` 
    * It should return http code 200 after get process completes

1. Apt exchange updating


1. Feature type update interface
    * Reference data:  `[    {        "featureProperties": [            {                "name": "freq",                "operators": [                    "<",                    "<=",                    "=",                    ">=",                    ">"                ],                "valueType": "STRING"            },            {                "name": "rec",                "operators": [                    "="                ],                "valueType": "STRING"            }        ],        "attributes": [            {                "attributeName": "FeatureAttr",                "supportedOperators": [                    "IN",                    "NOTIN"                ],                "valueType": "STRING"            },            {                "attributeName": "FeatureAttr2",                "supportedOperators": [                    "IN",                    "NOTIN"                ],                "valueType": "STRING"            }        ],        "featureType": "f6ba4bb6fbf31",        "urSourceType": "tenantid001.features.temp",        "scoreHost": "GRID",        "subSegmentGeneration": true,        "supportedRecencies": [        ],        "maxRecency": 30    }]` 
    * It should return http code 204 after update process completes

1. Feature type delete interface
    * Reference data:  `f6ba4bb6fbf32,f6ba4bb6fbf31,
1865,1864,` 
    * It should return http code 204 after delete process completes

1. Test Report Get API

    * It should return HTTP code 200 and Report object after the process finished

1. Test Report Get API Daily

    * It should return HTTP code 200 and Report object after the process finished    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmenta8affd670e9e","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_seat": "26053288866",    "status": "active"}`     * Reference data:  `{    "status": "inactive"}` 
    * It should return http code 200 after the updating process
    * It should return a Json string contains updated rmx exchange info
    * It should update apt exchange info in ACT_ACTIVE_SEGMENT

1. Rmx exchange updating
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegmentd20b416e3a64","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_owner": "371178",    "segment_seat": "371178",    "segment_shared_seats": ["39397", "97746", "184243", "122580", "131852", "479544"],    "status": "active"}`     * Reference data:  `{    "segment_owner": "371178",    "segment_seat": "371178",    "segment_shared_seats": ["184243", "39397"],    "status": "inactive"}` 
    * It should return http code 200 after the updating process
    * It should return a Json string contains updated rmx exchange info
    * It should update rmx exchange info in RMX_CATEGORY_MAPPING

1. Unsupported APT Exchange Publish
    * Reference data:  `{"type":"custom","description":"Targeting API Test","created_by":"Targeting API Test","expiration":"2013-12-12","name":"Yahoo/Genome/US/TestSegment0cd31a75ac1d","segment_properties":{"ref_segment_id":"65656","ref_provider":"Genome","segment_priority":"3"}}`     * Reference data:  `{    "segment_seat": "26053288866",    "status": "active"}`     * Reference data:  `{    "segment_seat": "26053288866",    "status": "pending"}` 
    * It should create an active apt exchange
    * It should return http code 400 after the update process completes