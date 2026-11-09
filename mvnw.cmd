@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM   http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@echo off
setlocal

if "%JAVA_HOME%"=="" (
    if exist "C:\Program Files\Zulu\zulu-21\bin\java.exe" (
        set "JAVA_HOME=C:\Program Files\Zulu\zulu-21"
        set "PATH=C:\Program Files\Zulu\zulu-21\bin;%PATH%"
    )
)

where mvn >nul 2>nul
if %ERRORLEVEL% equ 0 (
    call mvn %*
    exit /b %ERRORLEVEL%
)

set "MVN_VERSION=3.9.9"
set "MVN_DIR=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MVN_VERSION%"
set "MVN_CMD=%MVN_DIR%\apache-maven-%MVN_VERSION%\bin\mvn.cmd"

if not exist "%MVN_CMD%" (
    echo Downloading Apache Maven %MVN_VERSION%...
    powershell -NoProfile -Command "$ProgressPreference = 'SilentlyContinue'; New-Item -ItemType Directory -Force -Path '%MVN_DIR%' | Out-Null; Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MVN_VERSION%/apache-maven-%MVN_VERSION%-bin.zip' -OutFile '%MVN_DIR%\maven.zip'; Expand-Archive -Path '%MVN_DIR%\maven.zip' -DestinationPath '%MVN_DIR%' -Force; Remove-Item '%MVN_DIR%\maven.zip'"
)

call "%MVN_CMD%" %*
exit /b %ERRORLEVEL%
