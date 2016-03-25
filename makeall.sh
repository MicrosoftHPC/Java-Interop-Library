#!/bin/bash

SRC_ROOT=/soa

cd ${SRC_ROOT}/HpcServiceHost/src
bash makejar.sh

cd ${SRC_ROOT}/sample/HpcSoam/src
bash makejar.sh

cd ${SRC_ROOT}/sample/HpcSoamSvc/src
bash makejar.sh

cd ${SRC_ROOT}/sample/SampleService/src
bash makejar.sh

cd ${SRC_ROOT}/sample/SampleClient/src
bash RunTest.sh src

cd ${SRC_ROOT}
