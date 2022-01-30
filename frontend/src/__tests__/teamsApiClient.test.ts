import nock from 'nock';
import {createPerson, createTeam, getTeams} from "../teamsApiClient";

describe('teamsApiClient', () => {
  it('should make a GET request to get teams when getTeams is called', async () => {
    const expectedTeams = [{"name": 'first-team-name'}, {"name": 'second-team-name'}];
    nock('http://localhost').get('/teams').reply(200, expectedTeams);

    const teams = await getTeams();

    expect(teams).toEqual(expectedTeams);
  });

  it('should make a POST request to create a team when createTeam is called', async () => {
    const scope = nock('http://localhost', {
      reqheaders: {'Content-Type': 'application/json'}})
        .post('/team')
      .reply(200, {"id": 1,"name": "Team 1"});

    const response = await createTeam("Team 1");

    expect(scope.isDone()).toEqual(true);
    expect(response).toEqual({
      "id": 1,
      "name": "Team 1"
    });
  });

  it('should make a POST request to create a person when createPerson is called', async () => {
    const scope = nock('http://localhost', {
      reqheaders: {'Content-Type': 'application/json'}})
        .post('/person')
        .reply(200, {"id": 1,"name": "Person 1"});

    const response = await createPerson("Person 1");

    expect(scope.isDone()).toEqual(true);
    expect(response).toEqual({
      "id": 1,
      "name": "Person 1"
    });
  });
})