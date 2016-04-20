<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.in.VO.EcardVO" %>
<%@ page import="com.in.dbEntry.Card" %>
<style type="text/css">
    .TableHeaderClass{
        /*background: url('img/menu_band.JPG') 0 0 repeat-x;*/
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
<input type="hidden" id="testingModalDialogPassing" name="testingModalDialogPassing" value=""/>
<form id="categoryForm" name="categoryForm" action="./AdminFunctionalitiesServlet" method="post">
    <input type="hidden" name="categoryType" value="${requestScope.menuName}"/>
    <input type="hidden" name="categoryId" value="${requestScope.menuId}"/>
    <input type="hidden" name="typeOfDelete" value="category"/>
</form>
<form id="cardForm" name="cardForm" action="./AdminFunctionalitiesServlet" method="post">
    <input type="hidden" name="categoryType" value="${requestScope.menuName}"/>
    <input type="hidden" name="categoryId" value="${requestScope.menuId}"/>
    <input type="hidden" name="ecardId" id="ecardId" value=""/>
    <input type="hidden" name="typeOfDelete" value="card"/>
</form>
<form id="addCategoryForm" name="addCategoryForm" action="./AdminFunctionalitiesServlet" method="post">
    <input type="hidden" name="categoryType" value="${requestScope.menuName}"/>
    <input type="hidden" name="categoryId" value="${requestScope.menuId}"/>
    <input type="hidden" name="newCategoryName" id="newCategoryName" value=""/>
</form>
<form id="addCardForm" name="addCardForm" action="./AdminFunctionalitiesServlet" method="post">
    <input type="hidden" name="categoryType" value="${requestScope.menuName}"/>
    <input type="hidden" name="categoryId" value="${requestScope.menuId}"/>
</form>
<form id="ecardDisplayForm" name= "ecardDisplayForm" action="./CreateGreeting.jsp" method="post">
    <input type="hidden" id="displayCardId" name="displayCardId" value=""/>
    <input type="hidden" id="displayCardDesc" name="displayCardDesc" value=""/>
    <input type="hidden" id="displayCardType" name="displaycardType" value=""/>
</form>
<table width="100%" id="cardTable" cellspacing="0">
    <tr>
        <td width="100%" align="left" style="padding-left: 2px;" colspan="3"><c:if test="${link.linkType eq 'Administrator'}"> <a href="#" onMouseOut="swapImgRestore()" onMouseOver="swapImage('Image2','','images/b_add_category_o.gif',1)" onClick="return popupOfAddCardCategory('./AddCardCategory.jsp','AddCardCategory')"> <img src="images/b_add_category_u.gif" alt="Add Category" name="Image2" width="121" height="24" border="0" id="Image2" /> </a> </c:if>            </td>
    </tr>

    <c:forEach var="categoryVO" varStatus="begin" items="${requestScope.categoryVOs}" >
        <c:if test="${categoryVO.category_name eq requestScope.menuName}">
            <tr>
                <td width="100%" align="left" valign="top" class="TableHeaderClass" style="text-align:left; padding-left:5px">
                    <a> ${categoryVO.category_name} Cards </a>
                </td>
                <td width="1%" align="right" valign="top" class="TableHeaderClass" >
                    <c:if test="${param.userLinkType eq 'Administrator'}">
                        <a href="#" onMouseOut="swapImgRestore()" onMouseOver="swapImage('Image1','','images/b_add_card_o.gif',1)" onClick="popupOfAddCard();">
                            <img src="images/b_add_card_u.gif" alt="Add Card" name="Image1" width="102" height="24" border="0" id="Image1" />
                        </a>
                    </c:if>
                </td>
                <td width="1%" align="right" valign="top" class="TableHeaderClass" >
                    <c:if test="${param.userLinkType eq 'Administrator'}">
                        <a href="#" onMouseOut="swapImgRestore()" onMouseOver="swapImage('Image5','','images/b_delete_category_o.gif',1)" onClick="confirmCategoryDelete();">
                            <img src="images/b_delete_category_u.gif" alt="Delete Category" name="Image5" width="132" height="24" border="0" id="Image5" />
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" align="left" valign="top">
                    <img alt="" src="img/menu_band.JPG" width="100%" height="15">                        </td>
            </tr>
        </c:if>
    </c:forEach>
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
                Card card = new Card();
                ArrayList<EcardVO> ecardList = card.getCategoryCardInformation((String) request.getAttribute("menuId"));
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
                                        request.setAttribute("currentRow", row);

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
                                        <img src="./DisplayCardServlet?ecardId=${ecardId}" alt="Click here to send this card" width='300' height='200' border="0"/>                                            </a>                                        </td>
                            </tr>
                            <tr>
                                <td align="center" class="DetailsClass">
                                    <span class="CardContributorClass">Card Contributor:</span>&nbsp;&nbsp;${ecardContributor}
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image1${currentRow}${currentColoumn}','','images/b_remove_card_o.gif',1)" onclick="confirmCardDelete('${ecardId}');">
                                        <img src="images/b_remove_card_u.gif" alt="Remove Card" name="Image1${currentRow}${currentColoumn}" width="121" height="24" border="0" id="Image1${currentRow}${currentColoumn}" />
                                    </a>
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
                                        <img src="./DisplayCardServlet?ecardId=${ecardId}" alt="Click here to send this card" width='300' height='200' border="0"/>                                            </a>                                        </td>
                            </tr>
                            <tr>
                                <td align="center" class="DetailsClass">
                                    <span class="CardContributorClass">Card Contributor:</span>&nbsp;&nbsp;${ecardContributor}
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image1${currentRow}${currentColoumn}','','images/b_remove_card_o.gif',1)" onclick="confirmCardDelete('${ecardId}');">
                                        <img src="images/b_remove_card_u.gif" alt="Remove Card" name="Image1${currentRow}${currentColoumn}" width="121" height="24" border="0" id="Image1${currentRow}${currentColoumn}" />
                                    </a>
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
    function confirmCategoryDelete() {
        if(${requestScope.menuId eq '1'}){
            alert("Sorry, you cannot delete this category!!!!");
        }
        else{
            if (confirm("Are you sure you want to delete this category?")) {
                document.getElementById("categoryForm").submit();
            }
        }
    }

    function confirmCardDelete(ecardId) {
        if (confirm("Are you sure you want to delete this card?")) {
            document.getElementById("ecardId").value = ecardId;
            document.getElementById("cardForm").submit();
        }
    }

    function popupOfAddCardCategory(url,windowName)//when u click ardd category button
    {
        var width  = 400;
        var height = 200;
        var left   = (screen.width  - width)/2;
        var top    = (screen.height - height)/2;
        var params = 'width='+width+', height='+height;
        params += ', top='+top+', left='+left;
        params += ', titlebar=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=yes';
        params += ', toolbar=no';
        newwin=window.open(url,windowName, params);
        if (window.focus) {
            newwin.focus();
        }
        return false;
    }

    function redirectAfterAddCategory(){
        newwin.close();
        //alert(document.getElementById('newCategoryName').value);
        document.getElementById('addCategoryForm').submit();
    }

    function popupOfAddCard(){
        var obj = new Object();
        obj.subcategoryId = ${requestScope.menuId};
        var width  = "450px";
        var height = "300px";
        //var left   = (screen.width  - width)/2;
        //var top    = (screen.height - height)/2;
        var params = "dialogWidth: "+width+"; dialogHeight:" +height;
        //params += '; dialogTop:' +top+'; dialogLeft:' +left;
        params += "; center: yes";
        params += "; dialogHide: no";
        params += "; edge: raised";
        params += "; scroll: no";
        params += "; resizable: no";
        params += "; status: no";
        params += "; unadorned: yes";
        params += "; help: no";
        window.showModalDialog("AddCard.jsp", obj, params);
        //alert(obj.returnvalue);
        if(obj.returnvalue == "1"){
            document.getElementById("addCardForm").submit();
        }
    }

    function submitFormAndDisplayCard(ecardId,ecardDesc,ecardType) {
        //alert("Test" +ecardId + ecardDesc );
        document.getElementById("displayCardId").value = ecardId;
        document.getElementById("displayCardDesc").value = ecardDesc;
        document.getElementById("displayCardType").value = ecardType;
        document.getElementById("ecardDisplayForm").submit();
    }
</script>