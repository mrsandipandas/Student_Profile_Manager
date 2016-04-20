<%-- 
    Document   : Template
    Created on : Jan 27, 2010, 4:39:54 PM
    Author     : sada3260
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Start footer section-->
</td>
<!--<td align="right" valign="top" background="images/03_b.jpg"><img src="images/03_a.jpg" width="23" height="430" /></td>-->
</tr>
<tr>
    <td align="left" valign="top" bgcolor="#FFFFFF"><img src="images/04_a.jpg" width="792" height="17" /></td>
<!--    <td align="right" valign="top" background="images/03_b.jpg"><img src="images/04_b.jpg" width="23" height="17" /></td>-->
</tr>
<tr>
    <td align="center" valign="top" bgcolor="#FFFFFF" class="footer">
        <!--Start footer content-->
		This application is best viewed on Internet Explorer 7.x and higher, Mozilla 2.0 and higher at a screen resolution of 1152 x 864 Pixels or higher.
        <br />
        &copy; 2011-2012 for Netaji Subhas Engineering College.Developed By Sougata Das</td>
    <!--End footer content-->
    <td align="right" valign="top" background="images/03_b.jpg">&nbsp;</td>
</tr>
<tr>
    <td align="left" valign="top" bgcolor="#FFFFFF"><img src="images/05_a.jpg" width="792" height="14" /></td>
<!--    <td align="right" valign="top" background="images/03_b.jpg"><img src="images/05_b.jpg" width="23" height="14" /></td>-->
</tr>
</table></td>
</tr>
</table>
<script language="javascript">
    //==================================================================================================
    var color_bul = false;
    //==================================================================================================
    function show_color_picker(obj){
        try{
            if(color_bul == false){
                var str = "";
                str = str + "<table width='150' height='100' border='1' align='center' cellpadding='0' cellspacing='0' bordercolor='#000000'><tr>";
                str = str + "<td id='#FFFFFF' bgcolor='#FFFFFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFCCCC' bgcolor='#FFCCCC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFCC99' bgcolor='#FFCC99' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFFF99' bgcolor='#FFFF99' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFFFCC' bgcolor='#FFFFCC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#99FF99' bgcolor='#99FF99' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#99FFFF' bgcolor='#99FFFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CCFFFF' bgcolor='#CCFFFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CCCCFF' bgcolor='#CCCCFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFCCFF' bgcolor='#FFCCFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr><tr>";
                str = str + "<td id='#CCCCCC' bgcolor='#CCCCCC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FF6666' bgcolor='#FF6666' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FF9966' bgcolor='#FF9966' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFFF66' bgcolor='#FFFF66' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFFF33' bgcolor='#FFFF33' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#66FF99' bgcolor='#66FF99' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#33FFFF' bgcolor='#33FFFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#66FFFF' bgcolor='#66FFFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#9999FF' bgcolor='#9999FF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FF99FF' bgcolor='#FF99FF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr><tr>";
                str = str + "<td id='#C0C0C0' bgcolor='#C0C0C0' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FF0000' bgcolor='#FF0000' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FF9900' bgcolor='#FF9900' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFCC66' bgcolor='#FFCC66' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFFF00' bgcolor='#FFFF00' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#33FF33' bgcolor='#33FF33' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#66CCCC' bgcolor='#66CCCC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#33CCFF' bgcolor='#33CCFF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "";
                str = str + "<td id='#6666CC' bgcolor='#6666CC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CC66CC' bgcolor='#CC66CC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr><tr>";
                str = str + "<td id='#999999' bgcolor='#999999' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CC0000' bgcolor='#CC0000' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FF6600' bgcolor='#FF6600' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFCC33' bgcolor='#FFCC33' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#FFCC00' bgcolor='#FFCC00' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#33CC00' bgcolor='#33CC00' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#00CCCC' bgcolor='#00CCCC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#3366FF' bgcolor='#3366FF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#6633FF' bgcolor='#6633FF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CC33CC' bgcolor='#CC33CC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr><tr>";
                str = str + "<td id='#666666' bgcolor='#666666' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#990000' bgcolor='#990000' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CC6600' bgcolor='#CC6600' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#CC9933' bgcolor='#CC9933' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#999900' bgcolor='#999900' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#009900' bgcolor='#009900' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#339999' bgcolor='#339999' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#3333FF' bgcolor='#3333FF' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#6600CC' bgcolor='#6600CC' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#993399' bgcolor='#993399' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr><tr>";
                str = str + "<td id='#333333' bgcolor='#333333' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#660000' bgcolor='#660000' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#993300' bgcolor='#993300' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#996633' bgcolor='#996633' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#666600' bgcolor='#666600' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#006600' bgcolor='#006600' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#336666' bgcolor='#336666' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#000099' bgcolor='#000099' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#333399' bgcolor='#333399' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#663366' bgcolor='#663366' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr><tr>";
                str = str + "<td id='#000000' bgcolor='#000000' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#330000' bgcolor='#330000' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#663300' bgcolor='#663300' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#663333' bgcolor='#663333' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#333300' bgcolor='#333300' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#003300' bgcolor='#003300' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#003333' bgcolor='#003333' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#000066' bgcolor='#000066' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#330099' bgcolor='#330099' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "<td id='#330033' bgcolor='#330033' width='10' height='10' onclick='set_color(this)'><img width='1' height='1'></td>";
                str = str + "</tr></table>";
                var parent = obj.parentNode;
                var newdiv = document.createElement('div');
                var divIdName = "color_picker";
                newdiv.setAttribute('id',divIdName);
                newdiv.style.border 	= "0px solid #8C8F91";
                newdiv.style.position = "absolute";
                newdiv.style.width = "150px";
                newdiv.style.height = "10px";
                newdiv.style.zIndex = "1200";
                newdiv.style.left = findPos_left(obj);
                newdiv.style.top = findPos_top(obj)+23;
                newdiv.style.display = "block";
                newdiv.innerHTML = str;
                parent.appendChild(newdiv);
                color_bul = true;
            }else{
                var temp_obj = document.getElementById("color_picker");
                if(temp_obj!=null || temp_obj!="undefined"){temp_obj.style.display="block";}
            }
        }catch(e){}
    }
    //==================================================================================================
    function set_color(obj){
        try{
            var val = obj.id;
            var temp_obj1 = document.getElementById("color_picker");
            var temp_obj2 = document.getElementById("bg_color_val");
            var temp_obj3 = document.getElementById("content_tbl");
            if(temp_obj1!=null || temp_obj1!="undefined"){temp_obj1.style.display="none";}
            if(temp_obj2!=null || temp_obj2!="undefined"){temp_obj2.value=val;}
            if(temp_obj3!=null || temp_obj3!="undefined"){
                temp_obj3.style.backgroundColor = val;
            }
        }catch(e){}
    }
    //==================================================================================================
    function findPos_left(obj){
        try{
            var curleft = 0;
            if (obj.offsetParent){
                curleft = obj.offsetLeft;
                while (obj = obj.offsetParent){curleft += obj.offsetLeft;}
            }
            return curleft;
        }catch(e){}
    }
    //==================================================================================================
    function findPos_top(obj){
        try{
            var curtop = 0;
            if (obj.offsetParent){
                curtop = obj.offsetTop;
                while (obj = obj.offsetParent){curtop += obj.offsetTop;}
            }
            return curtop;
        }catch(e){}
    }
    //==================================================================================================

</script>
<map name="Map" id="Map"><area shape="rect" coords="379,2,400,22" alt="Home" href="/StudentREG/HomePageServlet" border="0" /></map>


</body>
<c:if test="${pageType ne 'displayGreetingPage'}">
    <script type="text/JavaScript">
        <!--
        preloadImages('images/b_add_card_o.gif','images/b_add_category_o.gif','images/b_back_o.gif','images/b_cancel_o.gif','images/b_delete_category_o.gif','images/b_delete_o.gif','images/b_hide_admin_o.gif','images/b_home_o.gif','images/b_next_o.gif','images/b_remove_card_o.gif','images/b_select_o.gif','images/b_send_o.gif','images/b_show_admin_o.gif','images/b_address_book_o.gif','images/b_close_o.gif','images/b_add_o.gif','images/b_cancel_o.gif','images/b_search_o.gif','images/b_upload_pic_o.gif');
        //-->
    </script>
</c:if>
</html>
<!--End footer section-->