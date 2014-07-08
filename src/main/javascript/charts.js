function index(req, resp) {
    resp.send({thing: "stuff", fooParam: req.params.foo});
}

