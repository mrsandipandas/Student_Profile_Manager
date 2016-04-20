
<link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
    var stockholm = new google.maps.LatLng(22.476227, 88.414986);
    var parliament = new google.maps.LatLng(22.476227, 88.414900);
    var marker;
    var map;

    function initialize() {
        var mapOptions = {
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            center: stockholm
        };

        map = new google.maps.Map(document.getElementById("map_canvas"),
        mapOptions);

        marker = new google.maps.Marker({
            map:map,
            draggable:true,
            animation: google.maps.Animation.DROP,
            position: parliament
        });
        google.maps.event.addListener(marker, 'click', toggleBounce);
    }

    function toggleBounce() {

        if (marker.getAnimation() != null) {
            marker.setAnimation(null);
        } else {
            marker.setAnimation(google.maps.Animation.BOUNCE);
        }
    }
</script>

<body onload="initialize();">
    <%@include file="template_header_home.jsp"%>
    <div id="map_canvas" style="width: 100%; height: 500px;">map div</div>
    <%@include file="template_footer.jsp"%>
</body>

