<style type="text/css">
    .dropDownStyle {
        height: 20px;
        width: auto;
        border: 1px solid #CA6F75;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #666666;
        padding-top:1px;
    }
</style>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${param.userLinkType eq 'BirthDayLink'}">
        <table width="100%">
            <tr>
                <td width="50%" align="left" valign="middle" nowrap="nowrap" style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 14px;color:#C66A6C; font-weight: bold;">
                    BirthDay Cards
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <table width="100%">
            <tr>
                <td width="10%" align="left" valign="middle" nowrap="nowrap" style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 11px;color:#C66A6C; font-weight: normal;">
                    Select a card Category:&nbsp;
                    <form id="cardCategoryForm" name="cardCategoryForm" action="./GreetingsCardServlet" method="post">
                        <input id="menuNameHiddenVal" type="hidden" name="menuNameHiddenVal" value=""/>
                        <input id="menuIdHiddenVal" type="hidden" name="menuIdHiddenVal" value=""/>
                        <input id="adminButtonStateHiddenVal" type="hidden" name="adminButtonStateHiddenVal" value="${requestScope.adminButtonState}"/>
                        <input id="linkTypeHiddenVal" type="hidden" name="linkTypeHiddenVal" value="${param.userLinkType}"/>
                    </form>
                </td>
                <td width="80%" align="left" valign="middle">
                    <select name="cardCategory" id="cardCategory" class="dropDownStyle" onchange="submitForm();">
                        <option value="-1" selected>Select a category</option>
                        <c:forEach var="categoryVO" items="${requestScope.categoryVOs}">
                            <c:choose>
                                <c:when test="${categoryVO.category_id eq requestScope.menuId}">
                                    <option value="${categoryVO.category_id}" SELECTED>${categoryVO.category_name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value='${categoryVO.category_id}'> ${categoryVO.category_name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td width="5%" align="right" valign="middle">
                    <c:choose>
                        <c:when test="${requestScope.adminButtonState eq 'admin'}">
                            <c:if test="${param.userLinkType eq 'Administrator'}"> <a id="HideAdminPrivilagesbutton" href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image7','','images/b_hide_admin_o.gif',1)" onclick="showAdminPrivButton();"> <img src="images/b_hide_admin_u.gif" alt="Hide Admin Privileges" name="Image7" width="162" height="24" border="0" id="Image7" /> </a> </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${param.userLinkType eq 'Administrator'}"> <a id="ShowAdminPrivilagesbutton" href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image13','','images/b_show_admin_o.gif',1)" onclick="hideAdminPrivButton();"> <img src="images/b_show_admin_u.gif" alt="Show Admin Privileges" name="Image13" width="162" height="24" border="0" id="Image13" /> </a> </c:if>
                            </c:otherwise>
                        </c:choose>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
    function submitForm(){
        var menu = document.getElementById("cardCategory");
        var menuId =  menu.options[menu.selectedIndex].value;
        var menuName = menu.options[menu.selectedIndex].text;
        document.getElementById("menuNameHiddenVal").value = menuName;
        document.getElementById("menuIdHiddenVal").value = menuId;
        document.getElementById("cardCategoryForm").submit();
    }

    function hideAdminPrivButton(){
        document.getElementById("adminButtonStateHiddenVal").value = 'admin';
        document.getElementById("ShowAdminPrivilagesbutton").style.display = 'none';
        submitForm();
    }

    function showAdminPrivButton(){
        document.getElementById("adminButtonStateHiddenVal").value = '';
        document.getElementById("HideAdminPrivilagesbutton").style.display = 'none';
        submitForm();
    }
</script>
