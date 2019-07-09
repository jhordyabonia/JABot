var engine;
function YTM(b)
{
    this.acounts= new Array();
    this.base=b;
    this.t=0;//base_size
    
    this.fillAcounts=function(n)
    {
         this.acounts[0]=this.base;
        for(this.t=1;this.t<n;this.t++)
            this.acounts[this.t]=this.base+(n-this.t);
    }
    this.addAcount=function(name)
    {
        this.acounts[this.t++]=name;
    }
    this.removeAcount=function(name)
    {
        for(W=0;W<this.t;++W)
            if(this.acounts[W]==this.base+name
            ||this.acounts[W]==this.base+name)
                this.acounts[W]="";
    }
}
function start(n)
{
    function send(vid)
    {
        document.title=n;
        $.ajax({
            content: engine,
            url: 'https://api.ytmonster.net/client/mark/'+n+'/'+ vid,
            success: function(data){console.log(data);},
            error: function() {$.ajax(engine);}
        });
    }
    $.ajax({
            url: 'https://api.ytmonster.net/client/watch/'+n,
            success: function(data)
            {
                var info = data.split('#');
                videoId = info[0].split('?v=');			
                            
                if(info[0].indexOf("youtube") > -1)
                {	
                    send( videoId[1]);
                    watched = 1;
                }else{watched = 0;}
                start(n);
            },
            error: function() {watched = 0; start(n);}
    });
}
var ytm=new YTM("jhordyabonia");
ytm.fillAcounts(7);
ytm.addAcount("manuafan1");
start(ytm.acounts[0]);
/*start(ytm.acounts[1]);
start(ytm.acounts[2]);
start(ytm.acounts[3]);
start(ytm.acounts[6]);
start(ytm.acounts[7]);
start(ytm.acounts[8]);*/
