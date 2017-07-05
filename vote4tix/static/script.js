var page = 0;

function getEvents(page,event_id) {
    
console.log("I'm in ");
    console.log(page);
  $('#events-panel').show();
  $('#attraction-panel').hide();

  if (page < 0) {
    page = 0;
    return;
  }
  if (page > 0) {
    if (page > getEvents.json.page.totalPages-1) {
      page=0;
      return;
    }
  }

  $.ajax({
    type:"GET",
    url:"https://app.ticketmaster.com/discovery/v2/events.json?keyword=concert&countryCode=US&apikey=gZJKzlGFcQjlaYsDC59Uk1mrD1uC27kV&size=4&page="+page,
    async:true,
    dataType: "json",
    success: function(json) {
          getEvents.json = json;
  			  showEvents(json);
  		   },
    error: function(xhr, status, err) {
  			  console.log(err);
  		   }
  });
}

function vote_fetch_events(event_id) {
    
console.log("I'm in ");
    console.log(page);
  $('#events-panel').show();
  $('#attraction-panel').hide();

  if (page < 0) {
    page = 0;
    return;
  }
  if (page > 0) {
    if (page > getEvents.json.page.totalPages-1) {
      page=0;
      return;
    }
  }

  $.ajax({
    type:"GET",
    url:"https://app.ticketmaster.com/discovery/v2/events.json?keyword="+event_id+"&countryCode=US&apikey=gZJKzlGFcQjlaYsDC59Uk1mrD1uC27kV&size=4&page="+page,
    async:true,
    dataType: "json",
    success: function(json) {
          getEvents.json = json;
  			  showEvents(json);
  		   },
    error: function(xhr, status, err) {
  			  console.log(err);
  		   }
  });
}

function showEvents(json) {
    console.log("I'm in a");
  var items = $('#events .list-group-item');
  items.hide();
  var events = json._embedded.events;
  var item = items.first();
  for (var i=0;i<events.length;i++) {
      console.log(events[i]);
    item.children('.list-group-item-heading').text(events[i].name);
    item.children('.list-group-item-text').text(events[i].dates.start.localDate);
    try {
      item.children('.venue').text(events[i]._embedded.venues[0].name + " in " + events[i]._embedded.venues[0].city.name);
    } catch (err) {
      console.log(err);
    }
    item.show();
    item.off("click");
    item.click(events[i], function(eventObject) {
      console.log(eventObject.data.id);
    localStorage.setItem( 'objectToPass', eventObject.data.id );
      try {
        getAttraction(eventObject.data._embedded.attractions[0].id);
      } catch (err) {
      console.log(err);
      }
    });
    item=item.next();
  }
}

$('#prev').click(function() {
  getEvents(--page);
});

$('.invite').click(function() {
  window.location.replace("/AddGroup.html");
});


$('.event_search').click(function() {
    
    var event_id = $(".event_search_text").val()
    console.log(event_id);
vote_fetch_events(event_id);
});

$('#next').click(function() {
  getEvents(++page);
});

function getAttraction(id) {
   
    console.log("I'm in b");
  $.ajax({
    type:"GET",
    url:"https://app.ticketmaster.com/discovery/v2/attractions/"+id+".json?apikey=gZJKzlGFcQjlaYsDC59Uk1mrD1uC27kV",
    async:true,
    dataType: "json",
    success: function(json) {
          showAttraction(json);
  		   },
    error: function(xhr, status, err) {
  			  console.log(err);
  		   }
  });
}

function showAttraction(json) {
    console.log("I'm in c");
 
  $('#events-panel').hide();
  $('#attraction-panel').show();
  
  $('#attraction .list-group-item-heading').first().text(json.name);
  $('#attraction img').first().attr('src',json.images[0].url);
  $('#classification').text(json.classifications[0].segment.name + " - " + json.classifications[0].genre.name + " - " + json.classifications[0].subGenre.name);
}

getEvents(page);