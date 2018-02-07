package contracts.userservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/users'
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        body(
                value(
                "totalElements": 1,
                "totalPages": 1,
                "content": [
                        value(
                                "userId": "test-user",
                                "name": "Test User",
                                "emailId": "testUser@test.com"
                        )
                ]
                )
        )
        headers {
            contentType('application/json')
        }
    }
}