describe '#daySummary', ->
    describe 'No day summary data from Chiron', ->
      Given 'we are mocking the  service call to return no day summary data', ->
        @fetchStub = sandbox.stub().returns(createMockFetchedData(undefined))
        @fetchProxy = proxyquire(link('/clients/ChironClient'), {'node-fetch': { default: @fetchStub }})
      When 'we call daySummary', -> 
        @result = @fetchProxy.ChironClient.daySummary('')
      Then 'the returned response is empty', -> 
        @result.then((any) -> should.equal(any, undefined))

    describe 'Get day summary data from Chiron', ->
      Given 'we are mocking the service call to return day summary data for a given test date', ->
        @fetchStub = sandbox.stub().returns(createMockFetchedData(mockActivityLog))
        @fetchProxy = proxyquire(link('/clients/ChironClient'), {'node-fetch': { default: @fetchStub }})
      When 'we call daySummary', -> 
        @result = @fetchProxy.ChironClient.fetchActivityLogs('TESTVENUE')
      Then 'the returned response should contain one or more activity logs', -> 
        @result.then( (any) -> 
          expect(any).to.be.mockActivityLog
          expect(any.length).to.be.above(0)
        )
