describe('loading the home page', () => {
    beforeEach(() => {
        cy.visit("http://localhost:8080");
    })

    it('loads the home page', () => {
        cy.findByText(/Team name/i).should('exist')
    })
})