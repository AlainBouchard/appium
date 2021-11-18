describe(
  'Demo for the Hotel Hopper team', () => {
    it('Use case from landing page to map page with hotel list', () => {
      cy.visit('/');
      
      cy.fixture('hotelLocation').then(({search, result, days}) => {
        // Using a Fixture as an example for the demo.
        cy
          .clickHotelButton()
          .typeLocation(search)
          .selectLocation(result)
          .pickDates(days)
          .clickDoneButton()
          .clickSearchButton();
      })
    })
  }
)