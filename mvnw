#!/bin/sh
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
set -e

if [ -z "$JAVA_HOME" ] && [ -d "/c/Program Files/Zulu/zulu-21" ]; then
  export JAVA_HOME="/c/Program Files/Zulu/zulu-21"
  export PATH="$JAVA_HOME/bin:$PATH"
fi

if [ -x "$(command -v mvn)" ]; then
  exec mvn "$@"
fi

MVN_VERSION="3.9.9"
MVN_DIR="$HOME/.m2/wrapper/dists/apache-maven-$MVN_VERSION"
MVN_BIN="$MVN_DIR/apache-maven-$MVN_VERSION/bin/mvn"

if [ ! -x "$MVN_BIN" ]; then
  mkdir -p "$MVN_DIR"
  curl -fsSL "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MVN_VERSION/apache-maven-$MVN_VERSION-bin.tar.gz" | tar -xz -C "$MVN_DIR"
fi

exec "$MVN_BIN" "$@"
