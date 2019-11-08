<%--
  Created by IntelliJ IDEA.
  User: jgrenierb
  Date: 2019-10-29
  Time: 2:59 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Spring's beauty</title>
  </head>
  <body>
  <p>Such beauty!</p>

  <form action="add3" method="get">
    <label>
      Email:
      <input type="email" name="email" required/>
    </label>
    <br/>

    <label>
      First name:
      <input type="text" name="firstName" required/>
    </label>
    <br/>

    <label>
      Last name:
      <input type="text" name="lastName" required/>
    </label>
    <br/>

    <label>
      <input type="submit" id="submit3" value="Send(connected to DB)"/>
    </label>
  </form>

  <form action="add4" method="post">
    <label>
      Email:
      <input type="email" name="email" required/>
    </label>
    <br/>

    <label>
      First name:
      <input type="text" name="firstName" required/>
    </label>
    <br/>

    <label>
      Last name:
      <input type="text" name="lastName" required/>
    </label>
    <br/>

    <label>
      <input type="submit" id="submit4" value="Send (connected to DB)"/>
    </label>
  </form>

  <form action="add1" method="get">
    <label>
      Email:
      <input type="email" name="email" required/>
    </label>
    <br/>

    <label>
      First name:
      <input type="text" name="firstName" required/>
    </label>
    <br/>

    <label>
      Last name:
      <input type="text" name="lastName" required/>
    </label>
    <br/>

    <label>
      <input type="submit" id="submit" value="Test (@RequestParam)"/>
    </label>
  </form>

  <form action="add2" method="get">
    <label>
      Email:
      <input type="email" name="email" required/>
    </label>
    <br/>

    <label>
      First name:
      <input type="text" name="firstName" required/>
    </label>
    <br/>

    <label>
      Last name:
      <input type="text" name="lastName" required/>
    </label>
    <br/>

    <label>
      <input type="submit" id="submit2" value="Test (@ModelAttribute)"/>
    </label>
  </form>

  </body>
</html>
