<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.in.VO.EcardVO" %>
<%@ page import="com.in.dbEntry.Card" %>
<style type="text/css">
    .TableHeaderClass{
        /* background: url('img/menu_band.JPG') 0 0 repeat-x;*/
        font-family: Verdana,Arial,Helvetica,sans-serif;
        font-size: 14px;
        color: #C66A6C;
        font-weight: bold;
    }
    .DetailsClass{
        font-family: Verdana,Arial,Helvetica,sans-serif;
        font-size: 13px;
        color: #C66A6C;
        font-weight: bold;
    }
    .CardContributorClass{
        font-family: Verdana,Arial,Helvetica,sans-serif;
        font-size: 11px;
        color: #C66A6C;
        font-weight: normal;
    }
</style>
<%
            Card card = new Card();
            ArrayList<EcardVO> ecardList = new ArrayList();
%>
<form id="cardDisplayForm" name="cardDisplayForm" action="./CreateGreeting.jsp" method="post">
    <input type="hidden" id="displayCardId" name="displayCardId" value=""/>
    <input type="hidden" id="displayCardDesc" name="displayCardDesc" value=""/>
    <input type="hidden" id="displayCardType" name="displaycardType" value=""/>
</form>
<table width="100%" id="cardTable" cellspacing="0">
    <c:choose>
        <c:when test="${param.userLinkType eq 'BirthDayLink'}">
            <%
                        ecardList = card.getCategoryCardInformation("1");
            %>
        </c:when>
        <c:otherwise>
            <c:forEach var="categoryVO" varStatus="begin" items="${requestScope.categoryVOs}" >
                <c:if test="${categoryVO.category_id eq requestScope.menuId}">
                    <tr>
                        <td  height="25px" class="TableHeaderClass" style="text-align:left; padding-left:5px">
                            <a>
                                ${categoryVO.category_name} Cards
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td  height="25px" class="TableHeaderClass">
                            <img alt="" src="img/menu_band.JPG" width="100%" height="15">
                        </td>
                    </tr>
                    <%
                                ecardList = card.getCategoryCardInformation((String) request.getAttribute("menuId"));
                    %>
                </c:if>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
<c:if test="${requestScope.menuId eq '-1' and param.userLinkType ne 'BirthDayLink'}">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
            <td align="center">
                <img src="images/card_theme.jpg" alt="Send a card with personalized message" width="736" height="345"/>
            </td>
        </tr>
    </table>
</c:if>
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <%
                Iterator iter = ecardList.iterator();
                int ecardListSize = ecardList.size();
                int noOfColoumns = 2;
                int noOfRows = (ecardListSize + 1) / 2;

                int row = 0;
                int coloumnCount = 0;
                while (row < noOfRows) {
                    pageContext.setAttribute("noOfColoumns", noOfColoumns);
    %>
    <tr>
        <c:forEach begin="1" end="${noOfColoumns}" step="1">
            <%
                                coloumnCount++;
                                if (coloumnCount > noOfColoumns) {
                                    row = (row + 1);
                                    coloumnCount = coloumnCount % noOfColoumns;
                                }
                                pageContext.setAttribute("currentColoumn", coloumnCount);

            %>
            <c:choose>
                <c:when test="${currentColoumn eq '1'}">
                    <%
                                        if (iter.hasNext()) {
                                            EcardVO ecardVO = ((EcardVO) iter.next());
                                            String ecardDesc = ecardVO.getEcardDescription();
                                            String ecardId = ecardVO.getEcardId();
                                            String ecardName = ecardVO.getEcardName();
                                            String ecardContributorName = ecardVO.getEcardContributorName();
                                            pageContext.setAttribute("ecardDesc", ecardDesc);
                                            pageContext.setAttribute("ecardId", ecardId);
                                            pageContext.setAttribute("ecardName", ecardName);
                                            pageContext.setAttribute("ecardContributor", ecardContributorName);
                    %>
                    <td align="center">
                        <table width="100%">
                            <tr>
                                <td align="center" class="DetailsClass">
                                    ${ecardName}
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <a href="#" onclick="submitFormAndDisplayCard('${ecardId}','${ecardDesc}','${requestScope.menuName}');">
                                        <img src="/StudentREG/DisplayCardServlet?ecardId=${ecardId}" alt="Click here to send this card" width='300' height='200' border="0"/>                                            </a>                                        </td>
                            </tr>
                            <tr>
                                <td align="center" class="DetailsClass">
                                    <span class="CardContributorClass">Card Contributor:</span>&nbsp;&nbsp;${ecardContributor}
                                </td>
                            </tr>
                        </table>
                    </td>
                    <%
                                        }
                    %>
                </c:when>
                <c:otherwise>
                    <%
                                        if (iter.hasNext()) {
                                            EcardVO ecardVO = ((EcardVO) iter.next());
                                            String ecardDesc = ecardVO.getEcardDescription();
                                            String ecardId = ecardVO.getEcardId();
                                            String ecardName = ecardVO.getEcardName();
                                            String ecardContributorName = ecardVO.getEcardContributorName();
                                            pageContext.setAttribute("ecardDesc", ecardDesc);
                                            pageContext.setAttribute("ecardId", ecardId);
                                            pageContext.setAttribute("ecardName", ecardName);
                                            pageContext.setAttribute("ecardContributor", ecardContributorName);
                    %>
                    <td align="center">
                        <table width="100%">
                            <tr>
                                <td align="center" class="DetailsClass">
                                    ${ecardName}
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <a href="#" onclick="submitFormAndDisplayCard('${ecardId}','${ecardDesc}','${requestScope.menuName}');">
                                        <img src="/StudentREG/DisplayCardServlet?ecardId=${ecardId}" alt="Click here to send this card" width='300' height='200' border="0"/>                                            </a>                                        </td>
                            </tr>
                            <tr>
                                <td align="center" class="DetailsClass">
                                    <span class="CardContributorClass">Card Contributor:</span>&nbsp;&nbsp;${ecardContributor}
                                </td>
                            </tr>
                        </table>
                    </td>
                    <%
                                        }
                    %>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
    <tr>
        <td colspan="2">
            <br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <br>
        </td>
    </tr>
    <%
                }
    %>
</table>
<script type="text/javascript">
    var newwin = null;
    function submitFormAndDisplayCard(ecardId,ecardDesc,ecardType) {
        //alert("Test" +ecardType );
        if(ecardType.toString() == ""){
            ecardType = 'Birthday';
        }
        //alert("$$$$$"+ ecardType);
        document.getElementById("displayCardId").value = ecardId;
        document.getElementById("displayCardDesc").value = ecardDesc;
        document.getElementById("displayCardType").value = ecardType;
        document.getElementById("cardDisplayForm").submit();
    }
</script>
