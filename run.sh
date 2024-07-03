#!/bin/sh
SECRET_FILE_PATH="./secret"
if [ -f "$SECRET_FILE_PATH" ]; then
    key=$(head -n 1 "$SECRET_FILE_PATH")
    export OPENAI_API_KEY=$key
else
    echo "Secret file not found at $SECRET_FILE_PATH"
    echo "\nPlease try this command below."
    echo "\$ echo \${YOUR_OPENAI_API_KEY} > secret"
    exit 1
fi

./gradlew build
java -jar ./app/build/libs/app.jar
