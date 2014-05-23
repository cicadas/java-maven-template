#!/bin/sh
xml=${1-/conf/pi_test/testng_api_func.xml}
java -cp "/jars/*:./*" org.testng.TestNG $xml