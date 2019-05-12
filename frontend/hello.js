function letsStart() {
  var map, marker;
  DG.then(function () {

    //Creating a map
    map = DG.map('map', {
      center: [43.230, 76.905],
      zoom: 13
    });

    let homeCoords = [43.229297, 76.901165];
    let uniCoords = [43.235066, 76.909597];

    // draggable merker for home



    //marker for uni
    DG.marker(uniCoords).addTo(map).bindPopup('UNIVER!');
    // setting coordinates




    turns = [
      [43.230564, 76.900628],
      [43.230814, 76.905081],
      [43.232244, 76.900285],
      [43.23387, 76.904705],
      [43.227757, 76.900982],
      [43.235308, 76.909179],
      [43.231111, 76.909737],
      [43.235543, 76.90449],
      [43.23591, 76.909136],
      [43.228422, 76.909957],
      [43.226241, 76.910456],
      [43.228129, 76.905633],
      [43.225944, 76.905993],
      [43.231803, 76.918776]
    ]
    for (let i = 0; i < turns.length; i++) {
      DG.marker(turns[i]).addTo(map).bindPopup((turns[i][0].toString() + " " + turns[i][1].toString()));
    }
    // for red line

    marker = DG.marker(homeCoords, {
      draggable: true
    })
      .on('drag', (e) => {
        latlngs[0][0] = e.target._latlng.lat;
        latlngs[0][1] = e.target._latlng.lng;

      })
      .addTo(map);
    /*
      console.log('Map work route', route);
      route = route.map(c => {
          c[0] = parseFloat(c[0]);
          c[1] = parseFloat(c[1]);
          return c;
      });*/



    // adding markers to turns


    //43.228422, 76.909957
    //43.229297, 76.901165
    //43.227757, 76.900982
    //43.228129, 76.905633

    var coordinates = {
      start: "43.227757, 76.900982",
      end: "43.235308, 76.909179",
      arrayOfCoordinates: [
        "43.229297, 76.901165",
        "43.235066, 76.909597",
        "43.230564, 76.900628",
        "43.227757, 76.900982",
        "43.232244, 76.900285",
        "43.230814, 76.905081",
        "43.228129, 76.905633",
        "43.228422, 76.909957",
        "43.231111, 76.909737",
        "43.23387, 76.904705",
        "43.235308, 76.909179"
      ],
      distances: [
        {
          "43.229297, 76.901165":
          {
            "43.230564, 76.900628": map.distance([43.229297, 76.901165], [43.230564, 76.900628]),
            "43.227757, 76.900982": map.distance([43.229297, 76.901165], [43.227757, 76.900982])
          }
        },
        {
          "43.230564, 76.900628": {
            "43.232244, 76.900285": map.distance([43.230564, 76.900628], [43.232244, 76.900285]),
            "43.230814, 76.905081": map.distance([43.230564, 76.900628], [43.230814, 76.905081])
          }
        },
        {
          "43.232244, 76.900285": {
            "43.23387, 76.904705": map.distance([43.232244, 76.900285], [43.23387, 76.904705])
          }
        },
        {
          "43.230814, 76.905081": {
            "43.231111, 76.909737": map.distance([43.230814, 76.905081], [43.231111, 76.909737]),
            "43.23387, 76.904705": map.distance([43.230814, 76.905081], [43.23387, 76.904705])
          }
        },
        {
          "43.227757, 76.900982": {
            "43.228129, 76.905633": map.distance([43.227757, 76.900982], [43.228129, 76.905633])
          }
        },
        {
          "43.228129, 76.905633": {
            "43.230814, 76.905081": map.distance([43.228129, 76.905633], [43.230814, 76.905081]),
            "43.228422, 76.909957": map.distance([43.228129, 76.905633], [43.228422, 76.909957])
          }
        },
        {
          "43.228422, 76.909957": {
            "43.231111, 76.909737": map.distance([43.228422, 76.909957], [43.231111, 76.909737])
          }
        },
        {
          "43.231111, 76.909737": {
            "43.235308, 76.909179": map.distance([43.231111, 76.909737], [43.235308, 76.909179])
          }
        },
        {
          "43.23387, 76.904705": {
            "43.235308, 76.909179": map.distance([43.23387, 76.904705], [43.235308, 76.909179])
          }
        }
      ]
    };

    var jsonDetails = JSON.stringify(coordinates);
    const xhr = new XMLHttpRequest();
    xhr.responseType = 'json';
    xhr.onload = () => {
      var hey = xhr.response;
      console.log('im response)))))', hey);
      let responseRoute = [];
      let temp = '';

      //string parsing
      hey.forEach(c => {
        responseRoute.push(c.split(', '));
      });

      console.log('showing response route');
      for (let i = 0; i < responseRoute.length; i++) {
        responseRoute[i][0] = parseFloat(responseRoute[i][0]);
        responseRoute[i][1] = parseFloat(responseRoute[i][1]);

      }
      console.log(responseRoute);

      var polyline = DG.polyline(responseRoute, { color: 'red' }).addTo(map);

    }

    xhr.open("POST", "http://localhost:8080/");
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonDetails);


  })

}

function updatePath() {

}