const http=require('http')

createServer(function (request,response){
    console.log('request come',request.url)

    response.end('hello world')
}).listen(8888)
console.log('server listening on 8888')

