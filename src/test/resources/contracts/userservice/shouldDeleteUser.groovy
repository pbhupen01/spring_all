package contracts.userservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'DELETE'
        url '/users/test-user'
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        headers {
            contentType('application/json')
        }
    }
}