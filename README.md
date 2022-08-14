 # URL을 입력받고 oEmbed 데이터를 수집하여 보여주는 서비스
<b>IDE : Intellij</b></br>
<b>기술 스택 : 
- Java11
- Spring Boot2.7.2
</br>

# Project Requirements
```
youtube, instagram, twitter, vimeo등의 컨텐츠를 미리보여주는 사이트

URL을 입력받고 oEmbed 데이터를 수집하여 보여주는 서비스

테스트 URL 리스트
- https://www.youtube.com/watch?v=dBD54EZIrZo
- https://www.instagram.com/p/BUawPlPF_Rx/
- https://twitter.com/hellopolicy/status/867177144815804416
- https://vimeo.com/20097015
```

# Running Application Locally
## Steps :
Gradle 빌드도구를 사용한 Spring boot Application 입니다.</br>
아래 명령어들을 사용하여 jar file을 빌드하고 실행할 수 있습니다.</br>

1) On the command line
    ```
    git clone https://github.com/doongjun/oembed-api.git
    ```
2) Inside Existing Gradle project
    ```
    File -> Import -> Gradle -> Existing Gradle project
    ```
    다음으로 `gradlew build` 명령어를 실행하여 빌드하고, `gradlew bootRun` 명령어를 실행합니다.
    ```
    gradlew build
    gradlew bootRun
    ```
    Intellij 내부에서 실행할 경우, Gradle 빌드 후에 `PreviewApplication` 메인 클래스를 마우스 오른쪽 버튼으로 클릭하고 `Run PreviewApplication`을 선택하여 애플리케이션을 실행해주세요.

3) Navigate main page

    브라우저에서 다음 링크에 접속해주세요. [http://localhost:8080](http://localhost:8080)
</br>

## Test :
![image](https://user-images.githubusercontent.com/66319788/184522693-76285eec-e361-4721-bb3f-5d92fab641c9.png)