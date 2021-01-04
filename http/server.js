const http = require('http')

http.createServer(function (request,response){
    console.log('request come',request.url)

    response.end('hello world')
})