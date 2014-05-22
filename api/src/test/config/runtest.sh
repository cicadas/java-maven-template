#!/bin/sh
xml=${1-/home/y/conf/targeting_api_test/testng_api_func.xml}
/home/y/bin/java -cp "/home/y/lib/jars/*:./*" org.testng.TestNG $xml