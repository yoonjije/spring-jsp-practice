# 1. 서블릿

    서블릿은 Java EE 스펙. 이것을 구현한 구현체가 tomcat, undertow, jetty.  

    웹 서버(Web Server)

    - 스태틱 파일 서빙
    - 로드 밸런싱
    - 리버스 프록시

    웹 애플리케이션 서버(Web Application Server)

    - 비즈니스 로직(애플리케이션)

    서블릿 구현체를 서블릿 컨테이너(Servlet Container)라고 부르고 그 구현체로:  

    - tomcat
    - undertow
    - jetty
    - 등등

 ## 1.1. 서블릿 기본 골격


    ```java
    @WebServlet("/path")  // 경로
    public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 서블릿 생성자
     */
    public HelloServlet() {
        super();
    }

  // WebServlet에 지정된 경로로 GET 메서드로 요청 시 처리할 로직
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

  // WebServlet에 지정된 경로로 POST 메서드로 요청 시 처리할 로직
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    }

    }
    ```

 ## 2.1. HttpServletRequest

    HTTP 요청에 대한 객체

    - 요청 라인
        - 메서드
        - URI
        - 프로토콜
    - 요청 헤더
    - 요청 바디

 ## 2.2. HttpServletResponse

    - 상태 라인(status line)
        - 상태 코드(status code)
    - 응답 헤더
    - 응답 바디

 ## 2.3. 서블릿 라이프사이클

    - init : 최초에 단 한 번 실행됨
    - service : 요청과 응답을 처리
    - destory : 메모리에서 해제 될 때 호출
        - 내용이 변경되어 컨텍스트 다시 로드 할 때
        - 서버 재시동
        - 서버 종료

 ## 2.4. 서블릿 작성 시 유의 사항

    - 서블릿은 싱글톤임.
        - 파괴되기 전 까지 남아 있음
        - 상태를 가지면 사이드 이펙트 발생 가능성 존재
         - 가급적 멤버 변수 두지 말 것

# 3. JSP

```jsp
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>환경설정넘모어렵다</title>
</head>
<body>
<%
	final Date date = new Date();
	final SimpleDateFormat sdf 
	= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss(E)");
%>
	<h1>._.</h1>
	<p>현재 시간: <%= sdf.format(date) %></p>
</body>
</html>
```

 위 코드는  크게 세 가지 부분으로 나뉨
    1. 디렉티브(directive)
    2. 스크립트 요소(Scriptlet, Expression)
    3. HTEML

  ## 3.1. 디렉티브
  `<%@`와 `%>`로 둘러 쌓인 코드가 디렉티브
  위 코드에서 디렉티브는 다음과 같다.

  ```jsp
  <%@ page import="java.text.SimpleDateFormat"%>
  <%@ page import="java.util.Date"%>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      Encoding="UTF-8"%>
 ```

 기본적인 사용 방법

 ```jsp
 <%@ 디렉티브이름 속성=값" ... 속성 = "값" %>
```

디렉티브는 해당 JSP의  설정 정보를 지정한다.

 - page : 해당 페이지에 대한  필요한 정보 지정. 자바 임포트, 응답 버퍼 사이즈, 문서 타입
 - taglib  : 해당 페이지에서 사용할 태그 라이브러리 지정
 - include : 다른 문서 임포트

 ## 3.2. 스크립트 요소

 JSP 페이지 내에서 동적으로 생성되는 내용들을 위해 존재함.

 - 표현식(Expression) : 값을 출력. 정확히 값만 출력함.
 - 스크립틀릿(Scriptlet) : 자바 코드 실행
 - 선언부(Declaration) : 자바 메서드 선언 
  
   ### 3.2.1.  표현식

   `<%=`와 `>`로 감싼 값으로 평가되는 식.

   ### 3.2.2. 스크립틀릿

   ```jsp
   <%
   final Date date = new Date();
   final SimpleDatedFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss(E)");
   %>
   ```

   ### 3.2.3. 선언부

    메서드를 선언할 수 있다.

    ```jsp
    <%!
    private String printDate() {
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss(E)");
        return sdf.format(date); 
    }
    %>
    ```

 ## 3.3 기본 객체

  - request(HttpServletRequest): HTTP 요청에 대한 객체
  - response(HttpServletResponse): HTTP 응답에 대한 객체. 응답 결과 가공 후 전송
  - session(HttpSession): 세션
  - application: 현재 애플리케이션(톰캣)의 정보
  - pageContext: 현재 페이지 구성에 대한 컨텍스트