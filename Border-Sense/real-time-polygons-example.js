                var myMap;
                var myObject = new Array();
                var myInterval;
                var myInterval2;
                var intervalCounter =0;
                var intervalCounter2 =0;
                var myInfoWindow;
                var centerpoint;
                var map;




                jQuery(document).ready(function(){
                    initialize();
                });

            /*

                function initialize() {
                    var center = new google.maps.LatLng(59.875405, 10.842099);
                    var zoom = 10;
                    map = new google.maps.Map(document.getElementById('map-canvas'), {
                        zoom: zoom,
                        center: center,
                        mapTypeId: google.maps.MapTypeId.HYBRID
                    });
                }

                */
                function mapAnimate(){

                 //   google.maps.event.addDomListener(document.getElementById('sc2'), 'onclick', mapAnimate());
                 myMap.setCenter(new google.maps.LatLng(29.701152, -101.371737));

        //         myMap.setCenter(new google.maps.LatLng(30.666467, 74.196575));
        myMap.setZoom(13);

                 //   google.maps.event.addListener(map, 'tilesloaded', function() {
                 //       window.setTimeout(function() {
                  //          map.panTo(new google.maps.LatLng(35.4639008,77.04390119999994));
                  //      }, 100);
                   // });
            }


                // although we are using a static data source consisting of 4 pairs of
                // lat/lon/elevation, we could have used an Ajax call to get such data
                // from a database or a middleware or API. This array is just to show
                // an example of polygon data source
                var dataSource = [
               //  "-90.00012,45.00045,0 -90.00012,45.00060,0 -89.99988,45.00060,0 -89.99988,45.00045,0",
                //"-89.99919,45.00111,0 -89.99919,45.00119,0 -89.99896,45.00119,0 -89.99896,45.00111,0",
                //"-89.99826,45.00197,0 -89.99826,45.00206,0 -89.99803,45.00206,0 -89.99803,45.00197,0",
                "77.02102661132812,35.50316417759601,0 77.05810546875,35.50316417759601,0 77.05535888671875,35.47856499535729,0 77.01141357421875,35.47856499535729,0",

                "77.10479736328125,35.449483527031475,0 77.13226318359375,35.44836479904722,0 77.13226318359375,35.430463036438276,0 77.10617065429688,35.430463036438276,0",

                "77.11578569140624,35.345375322554425,0 77.11029252734374,35.38457160381764,0 77.08694658007812,35.334172889944135,0 77.080080125,35.37673387014177,0 77.0993081992188,35.36329598397906,0",
                //"77.1185302734375,35.43437938715429,0 77.13569641113281,35.43437938715429,0 77.13569641113281,35.42654649525765,0 77.11990356445312,35.42654649525765,0",

                "77.15972900390625,35.392408576059616,0 77.18719482421875,35.39352808136064,0 77.18719482421875,35.37673387014179,0 77.16110229492188,35.37449437781793,0",

                "77.15423583984375,35.357696204467516,0 77.13088989257812,35.35881619143943,0 77.13226318359375,35.34425514918409,0 77.15560913085938,35.34425514918409,0",

                "77.200927734375,35.34425514918409,0 77.22427368164062,35.34649548039281,0 77.21466064453125,35.32633026307483,0",

            //"77.11990356445312,35.51434313431818,0 77.14599609375,35.51434313431818,0 77.14599609375,35.504282143299655,0 77.1185302734375,35.50316417759601,0",

            "77.20367431640625,35.30840140169162,0 77.24212646484375,35.30840140169162,0 77.24212646484375,35.285984736065735,0 77.200927734375,35.28374272801905,0",

            "77.20916748046875,35.21196570103912,0 77.23663330078125,35.20972164522138,0 77.23114013671875,35.19401151791166,0 77.1954345703125,35.191766965947394,0",

            "77.3272705078125,35.09519259251624,0 77.36297607421875,35.097439809364204,0 77.36572265625,35.068221159859256,0 77.32452392578125,35.05922870088872,0",
             "77.17620849609375,34.98837848142154,0 77.19131469726562,35.000753578642396,0 77.20504760742188,34.986128262717195,0 77.18994140625,34.97600151317591,0",
              "77.7447509765625,34.84874803007872,0 77.77908325195312,34.84536693184101,0 77.77084350585938,34.823950084069025,0 77.74200439453125,34.82845935709055,0",
              "77.41928100585938,34.90395296559006,0 77.42889404296875,34.88705743313571,0 77.41104125976562,34.88029824720345,0 77.40005493164062,34.89606881622971,0",

              "77.45635986328125,34.82845935709055,0 77.46322631835938,34.80929324176269,0 77.43850708007812,34.800272350556824,0 77.43301391601562,34.820567967282706,0",

            "77.35610961914062,34.98837848142154,0 77.32864379882812,34.986128262717195,0 77.34375,34.97037500007788,0",
            // some  "77.4577331542968834.972625651697065,0 77.48519897460938,34.97487624147657,0 77.48519897460938,34.95574425733425,0 77.464599609375,34.954618707382416,0"

            "77.48382568359375,34.942236637841184,0 77.508544921875,34.942236637841184,0 77.508544921875,34.93097858831627,0 77.486572265625,34.928726792983845,0",



            ];
            //polygons for scenario 2
         //   var dataSource1=[

           // "74.20183181762695,30.67279945913991,0 74.20251846313477,30.67279945913991,0 74.20251846313477,30.672467257285764,0 74.20187473297119,30.67243034589811,0",
           // "74.19719696044922,30.671470644868947,0 74.19779777526855,30.671470644868947,0 74.19779777526855,30.671064614640446,0 74.19723987579346,30.671027702716856,0",
           // "74.19084548950195,30.668702223099785,0 74.19144630432129,30.66877604870981,0 74.19148921966553,30.66818544225015,0 74.19080257415771,30.668148529226546,0",
            //"74.1992998123169,30.665970635873126,0 74.19861316680908,30.665970635873126,0 74.19861316680908,30.66556458253049,0 74.1994714736938,30.66556458253049,0",
            //];

            var dataSource1=[


            "-101.36715888977051,29.703859097139357,0 -101.36672973632812,29.702964464637105,0 -101.36587142944336,29.703188123509737,0 -101.36621475219727,29.70423185833003,0",
            "-101.36286735534668,29.7053501336009,0 -101.36226654052734,29.70445551438034,0 -101.36372566223145,29.704306410402122,0",
            "-101.35754585266113,29.706841147925676,0 -101.35711669921875,29.705946541987238,0 -101.35788917541504,29.70572288925743,0 -101.3584041595459,29.70646839641982,0",
            "-101.35256767272949,29.708853982148547,0 -101.35376930236816,29.70840668914131,0 -101.35256767272949,29.707735745894635,0",
            "-101.35136604309082,29.711090417297648,0 -101.35050773620605,29.71027039686008,0 -101.34990692138672,29.711090417297648,0 -101.35076522827148,29.711910431038035,0",
            "-101.34964942932129,29.71496678688531,0 -101.35076522827148,29.713848618710283,0 -101.34939193725586,29.713550438426918,0",
            "-101.34930610656738,29.71794850779653,0 -101.34964942932129,29.716979458215132,0 -101.34844779968262,29.716979458215132,0 -101.34819030761719,29.71772488180038,0",
            "-101.3485336303711,29.723837146389066,0 -101.34801864624023,29.72264453862633,0 -101.34939193725586,29.722793615371746,0",

        // UGS

        "-101.36664390563965,29.708853982148547,0 -101.36587142944336,29.707810295365647,0 -101.36484146118164,29.708332140113054,0 -101.36552810668945,29.70937582147209,0",
        "-101.3792610168457,29.71280498384388,0 -101.37823104858398,29.711910431038035,0 -101.37737274169922,29.71243225447672,0 -101.37831687927246,29.713326802633183,0",
        "-101.36003494262695,29.71675583006012,0 -101.36140823364258,29.71675583006012,0 -101.36140823364258,29.716084942605974,0 -101.35994911193848,29.716010399278726,0",
        "-101.35394096374512,29.71981203838945,0 -101.35428428649902,29.718693924185484,0 -101.35514259338379,29.718917548022702,0 -101.35488510131836,29.72025928058346,0",
        "-101.34535789489746,29.709972205949644,0 -101.3444995880127,29.71101587026187,0 -101.34381294250488,29.709823110162326,0",
        "-101.34346961975098,29.71623402909437,0 -101.34183883666992,29.716159485877828,0 -101.3419246673584,29.715190419025785,0 -101.3433837890625,29.715190419025785,0",
        "-101.35591506958008,29.727563954297256,0 -101.35668754577637,29.72719127973507,0 -101.35574340820312,29.72637139082601,0 -101.35505676269531,29.72681860378871,0",
        //UGS not on Alert-

        "-101.3532543182373,29.72338992013892,0 -101.35334014892578,29.722048229430218,0 -101.3517951965332,29.722122768273493,0 -101.35196685791016,29.723538995777094,0",
        "-101.34527206420898,29.729725439461582,0 -101.34501457214355,29.728458367599035,0 -101.3459587097168,29.728234765021053,0 -101.34638786315918,29.72935277292778,0",
        "-101.37462615966797,29.726222319395504,0 -101.3737678527832,29.724657056004606,0 -101.37248039245605,29.725402422569125,0 -101.3734245300293,29.72719127973507,0",
        "-101.38089179992676,29.72502973997892,0 -101.3789176940918,29.72570056764474,0 -101.38003349304199,29.727265814758248,0",
        "-101.37265205383301,29.718321216682956,0 -101.37385368347168,29.71705400082276,0 -101.37248039245605,29.71630857225554,0 -101.37145042419434,29.717352170699705,0",
        "-101.33831977844238,29.72048290093316,0 -101.3367748260498,29.72025928058346,0 -101.33728981018066,29.718992089191065,0 -101.33866310119629,29.719066630304088,0",

        ];


                // set up the map by creating map options (which contains a center point)
                function initialize() {
                    centerpoint = new google.maps.LatLng(35.4639008,77.04390119999994);


                //   centerpoint = new google.maps.LatLng(30.666467, 74.196575);
                var mapOptions = {
                    zoom: 9,
                    center: centerpoint,
                    mapTypeId: google.maps.MapTypeId.HYBRID
                }
                myMap = new google.maps.Map(
                    document.getElementById('map-canvas'), 
                    mapOptions
                    );

                    // initialize an information window that can be constantly reused
                    myInfoWindow = new google.maps.InfoWindow();

                    // draw the button to try again
                    createSelectionControl();

                    // start the animation to display each polygon
                    myInterval = setInterval(function() { drawPolygonGreen(); }, 300);
                }
                

                
                // if this script is invoked from the <body>, invoke the initialize 
                // function now
                //(function(){ initialize(); })();
                // jQuery method

            /*
                $(document).ready(function() {
                    initialize();
                });
                */


                // use the code below if you are using this script in <head></head> tag
                // google.maps.event.addDomListener(window, 'load', initialize);


                // --------------------------

                /**
                 * Make sure that the input box is set a control centered on the map
                 */
                 function createSelectionControl() {
                    var dd = document.getElementById('drawagain');
                    myMap.controls[google.maps.ControlPosition.TOP_CENTER].push(dd);
                }

                /**
                 * Clear the map by removing all polygons
                 */
                 function clearMap() {
                    var objects = myObject.length;
                    for(var i =0; i < objects; i++) {
                        myObject[i].setMap(null);
                    }
                    myObject.length =0;
                }

                /**
                 * Draw the polygons again
                 */
                 function drawAgain() {
                    intervalCounter =0;
                    clearMap();
                    clearInterval(myInterval);
                  //  myInterval = setInterval(function() { drawPolygon(); }, 100);   
                  drawPolygon(0,'red');
                  drawPolygon(1,'yellow');
                  drawPolygon(2,'yellow');
                  //  myInterval = setInterval(function() { drawPolygon(1,'yellow'); }, 100);
                   // myInterval = setInterval(function() { drawPolygon(2,'yellow'); }, 100);
                   // myInterval = setInterval(function() { drawPolygon(3,'yellow'); }, 100);

               }


               function scenario1() {


                var elem = document.getElementById("mytext");
                elem.value = "11";

                var elem = document.getElementById("notify");
                elem.value = "UGS alert. Keeping the near by sensors on Notify";

                //clearMap();
                clearInterval(myInterval);
                drawPolygon(0,'red');


                var yellowint= setInterval(function() { 
                    drawPolygon(1,'yellow');
                    drawPolygon(2,'yellow');
                    drawPolygon(3,'yellow');

                   // drawPolygon(2,'red'); 
               }, 5000);


                for (var i=4;i<dataSource.length;i++){
                    drawPolygon(i,'#66FF33');
                }

                myintv=setInterval(function() { clearMap();  }, 16000);
                
                
                //setInterval(function() { clearMap(); }, 200);

                setInterval(function() { 
                   clearInterval(yellowint);
                   for (var i=0;i<4;i++){
                    drawPolygon(i,'red');
                }
                   // drawPolygon(2,'red'); 
               }, 9000);

                setInterval(function() { 
                    for (var i=4;i<dataSource.length;i++){
                        drawPolygon(i,'yellow');
                    }
                   // drawPolygon(2,'red'); 
               }, 9000);
                clearInterval(myintv);
                valueinterval=setInterval(function() {
                    var elem = document.getElementById("mytext");
                    elem.value = "14"; 

                    var elem = document.getElementById("notify");
                    elem.value = "3 more alerts detected. Putting all the sensors on Notify mode"; }, 9000);

            //clearInterval(valueinterval);

        }



        function scenario2() {

            mapAnimate();
               // initialize2();
               var elem = document.getElementById("mytext");
               elem.value = "10";

               var elem2 = document.getElementById("notify");
                elem2.value =" ";


               clearMap();
               myInterval2 = setInterval(function() { drawPolygonGreen2(); }, 100);
             //  drawPolygonGreen2();
            //  clearInterval(valueinterval);

            var intervalinit= setInterval(function() { 



                 drawPolygonScenario2(0,'red');
                 drawPolygonScenario2(1,'yellow');
                 drawPolygonScenario2(2,'yellow');
                 drawPolygonScenario2(3,'yellow');

                 for (var i=4;i<dataSource1.length;i++){
                    drawPolygonScenario2(i,'#66FF33'); }

                }, 8000);
              var  valueinterval1=setInterval(function() {
                         elem = document.getElementById("mytext");
                        elem.value = "11";

                     var elem2 = document.getElementById("notify");
                    elem2.value = "Under Water alert. Keeping the near by sensors on Notify";
            }, 9000);




           //  myintv=setInterval(function() { clearMap(); }, 3000);


                //setInterval(function() { clearMap(); }, 200);

               var myinterval3= setInterval(function() { 
                    clearInterval(myInterval2);
                    clearInterval(intervalinit);
                    drawPolygonScenario2(1,'red');
                    


                    for (var i=2;i<4;i++){
                       drawPolygonScenario2(i,'yellow');
                       drawPolygonScenario2(8,'yellow');
              }
                   // drawPolygon(2,'red'); 
               }, 16000);


              //  clearInterval(myintv);
               var myinterval4=setInterval(function() {
                     elem = document.getElementById("mytext");
                    elem.value = "12"; 
                    clearInterval(valueinterval1);
                 elem2 = document.getElementById("notify");
                elem2.value ="Under Water alert. Keeping the near by sensors on Notify";
        }, 16000);

          

           var myinterval5= setInterval(function() { 
                  //  clearInterval(myInterval2);
                 //   clearInterval(myInterval3);
                   clearInterval(myinterval4);
                 //   clearInterval(intervalinit);

                 elem = document.getElementById("mytext");
                elem.value = "14"; 

                 elem2 = document.getElementById("notify");
                elem2.value ="No further Under Water alert.UGS activated.Keeping the near by sensors on Notify";

                    drawPolygonScenario2(8,'red');
                    for (var i=9;i<15;i++){
                       drawPolygonScenario2(i,'yellow');
              }
                   // drawPolygon(2,'red'); 
               }, 24000);


              //  clearInterval(myintv);
            //  var myinterval6 =setInterval(function() {
                 //   clearInterval(myInterval5);
               
     //   }, 24000);

        }


                /**
                 * Draw polygons on the map
                 */
                 function drawPolygon(droneno,color) {
                    // get an item from the array, parse information and then have the
                    // parser display the polygon on the map
                   // parseInformation(dataSource[intervalCounter],'red');
                   parseInformation(dataSource[droneno],color);
                    // if we have gone through all of our array items, let's stop
                    // the interval
                    intervalCounter++;
                    if (intervalCounter >= dataSource.length) {
                        clearInterval(myInterval);
                    }
                }

                function drawPolygonScenario2(droneno,color) {
                    // get an item from the array, parse information and then have the
                    // parser display the polygon on the map
                   // parseInformation(dataSource[intervalCounter],'red');
                   parseInformation(dataSource1[droneno],color);
                    // if we have gone through all of our array items, let's stop
                    // the interval
                    intervalCounter++;
                    if (intervalCounter >= dataSource1.length) {
                        clearInterval(myInterval);
                    }
                }

                function drawPolygonGreen() {
                    // get an item from the array, parse information and then have the
                    // parser display the polygon on the map
                    parseInformation(dataSource[intervalCounter],'#66FF33');
                    // parseInformation(dataSource[droneno],'red');
                    // if we have gone through all of our array items, let's stop
                    // the interval
                    intervalCounter++;
                    if (intervalCounter >= dataSource.length) {
                        clearInterval(myInterval);
                    }
                }

                function drawPolygonGreen2() {
                    // get an item from the array, parse information and then have the
                    // parser display the polygon on the map
                    parseInformation(dataSource1[intervalCounter2],'#66FF33');
                    // parseInformation(dataSource[droneno],'red');
                    // if we have gone through all of our array items, let's stop
                    // the interval
                    intervalCounter2++;
                    if (intervalCounter2 >= dataSource1.length) {
                        clearInterval(myInterval);
                    }
                }

                /**
                 * Parse information that's available in the array and draw it on the
                 * screen; also populate it in myObject array
                 * 
                 * @param string data 4 pairs of Lat/Lon/Elev information as string
                 */
                 function parseInformation(data,color) {
                    var polygon = setupPolygon(data);
                    myObject.push(
                        new google.maps.Polygon({
                            paths: polygon,
                            strokeColor: color,
                            strokeOpacity: 1,
                            strokeWeight: 2,
                            fillColor: color,
                            fillOpacity:0.6
                        })
                        );
                    var obj = myObject[myObject.length - 1];
                    obj.setMap(myMap);
                    google.maps.event.addListener(obj, 'click', showInformation);
                }

                /**
                 * Given 4 pairs of lat/lon/elev information, split each pair by space
                 * and from each pair, split by a comma to get lat, lon and elevation.
                 * Use that information to draw a polygon
                 * 
                 * @param string latlonstr 4 pairs of Lat/Lon/Elev information as string
                 *
                 * @returns array Google Map lat lon for each pair of lat/lon/elev
                 */
                 function setupPolygon(latlonstr) {
                    var latlonstr = latlonstr.trim();
                    var individualPoints = latlonstr.split(' ');
                    var individualPointsLength = individualPoints.length;
                    var point = new Array();
                    var returnData = new Array();
                    for (var i =0; i < individualPointsLength; i++) {
                        point = individualPoints[i].split(',');
                        returnData[i] = new google.maps.LatLng(
                            parseFloat(point[1]), 
                            parseFloat(point[0])
                            );
                    }
                    return returnData;
                }

                /**
                 * Show information box when a polygon is clicked
                 */
                 function showInformation(event) {
                    var message = getMessage(this, false);
                    myInfoWindow.setOptions({ content: message });
                    myInfoWindow.setPosition(event.latLng);
                    myInfoWindow.open(myMap);
                }

                /**
                 * Get appropriate message for a given polygon
                 * 
                 * @param polygon polygon Google Map polygon object
                 * 
                 * @return Message appropriate for the polygon
                 */
                 function getMessage(polygon) {
                    var coordinates = polygon.getPath().getArray();

                    var message = 'Sensor - Active ,<br> Sending data periodically';

                    var coordinateMessage = '<p>Location is <br>';
                    var consoleCoordinates = '';
                    for (var i =0; i < coordinates.length; i++) {
                        coordinateMessage += coordinates[i].lat() 
                        + ', ' + coordinates[i].lng() + '<br>';
                        consoleCoordinates += 'new google.maps.LatLng(' 
                            + coordinates[i].lat() 
                            + ', ' + coordinates[i].lng() + '),\n';
            }
            message += coordinateMessage;

            return message;
        }

                /**
                 * Get area of a polygon in acres
                 */
                 function GetArea(poly) {
                    var result = parseFloat(google.maps.geometry.spherical.computeArea(
                        poly.getPath()
                        )) *0.000247105;
                    return result.toFixed(4);
                }






            //google.maps.event.addDomListener(document.getElementById('sc2'), 'onclick', mapAnimate);