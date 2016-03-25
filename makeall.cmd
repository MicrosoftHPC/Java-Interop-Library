@echo off

set SRC_ROOT=C:\HPCSOA

cd %SRC_ROOT%\HpcServiceHost\src
call makejar.cmd

cd %SRC_ROOT%\sample\HpcSoam\src
call makejar.cmd

cd %SRC_ROOT%\sample\HpcSoamSvc\src
call makejar.cmd

cd %SRC_ROOT%\sample\SampleService\src
call makejar.cmd

cd %SRC_ROOT%\sample\SampleClient\src
call RunTest.cmd src

cd %SRC_ROOT%
