/*
 * SimpleModal Basic Modal Dialog
 * http://www.ericmmartin.com/projects/simplemodal/
 * http://code.google.com/p/simplemodal/
 *
 * Copyright (c) 2010 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Revision: $Id: basic.js 254 2010-07-23 05:14:44Z emartin24 $
 */

jQuery(function ($) {
    // Load dialog on page load
    //$('#basic-modal-content').modal();

    // Load dialog on click
    $('#basic-modal').click(function (e) {
        $('#basic-modal-content').modal();

        return false;
    });

    
});
function sendIndividualSMS(userName) {
    document.getElementById('smsusername').value = userName;
    $('#basic-modal-content-sms').modal();
    return false;
}

function sendGroupSMS() {
    $('#basic-modal-content-groupsms').modal();
    return false;
}

function sendIndividualMail (userName){
    document.getElementById('emailusername').value = userName;

    $('#basic-modal-content-email').modal();

    return false;
}

function sendGroupMail (){
    $('#basic-modal-content-groupemail').modal();
    return false;
}