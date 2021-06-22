<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>PMS</title>
        <script src="https://kit.fontawesome.com/1121c369ff.js" crossorigin="anonymous"></script>
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=request.getContextPath()%>/css/table.css" TITLE="style" />
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=request.getContextPath()%>/css/style.css" TITLE="style" />
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=request.getContextPath()%>/css/tooltip.css" TITLE="style" />
        <LINK REL="stylesheet" TYPE="text/css" HREF="<%=request.getContextPath()%>/css/button.css" TITLE="style" />
    </head>
    <body>
        <c:import url="/view/header.jsp"/>
        <div class="container">
        	<table>
        		<thead>
        			<tr>
        				<th>id</th>
        				<th>name</th>
        				<th>description</th>
        				<th>cost</th>
        				<th>date</th>
        				<th>customers</th>
        				<th>companies</th>
        				<th>developers</th>
        				<td colspan="2" align="center"></td>
        			</tr>
        		</thead>
        		<tbody>
                 <c:forEach var="project" items="${projects}">
                     <tr>
                         <td>${project.id}</td>
                         <td>${project.name}</td>
                         <td>${project.description}</td>
                         <td>${project.cost}</td>
                         <td>${project.date}</td>
                         <td>
                             <div class="tooltip">${project.customers.size()}
                               <span class="tooltiptext">${project.customers}</span>
                             </div>
                         </td>
                         <td>
                             <div class="tooltip">${project.companies.size()}
                               <span class="tooltiptext">${project.companies}</span>
                             </div>
                         </td>                         <td>
                             <div class="tooltip">${project.developers.size()}
                               <span class="tooltiptext">${project.developers}</span>
                             </div>
                         </td>
                         <td> <a href="/projects/findById?id=${project.id}">
                                 <button>Details</button>
                              </a>
                         </td>
                         <td> <a href="">
                                 <button>Update</button>
                              </a>
                         </td>
                     </tr>
                 </c:forEach>
        		</tbody>
        	</table>
        </div>
    </body>
</html>
