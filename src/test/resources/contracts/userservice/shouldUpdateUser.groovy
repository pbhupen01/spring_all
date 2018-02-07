package contracts.userservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'PUT'
        url '/users'
        body([
                "userId": "test-user",
                "name":"Utest User",
                "password":"test-user",
                "emailId":"utest@test.com"
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        body([
                value(
                        "userId": "test-user",
                        "name":"Utest User",
                        "emailId":"utest@test.com"
                )
        ])
        headers {
            contentType('application/json')
        }
    }
}