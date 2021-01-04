const http=require("http")
const fs=require("fs")

http.createServer(function (request,response){
    console.log('request come',request.url)
    const html=fs.readFileSync('index.html','utf8')
    response.writeHead(200,{
        'Content-Type': 'text/html'
    })
    response.end(html)
}).listen(8888)

console.log('server listening on 8888')

/*
const http = require("http")
 
const server = http.createServer(function(req, res) {
  // ......
  console.log('request come',request.url)

    response.end('hello world')
});
 
server.listen(3000)
console.log('server listening on 3000')
*/