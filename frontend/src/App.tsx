import React, { FormEvent, useEffect, useState } from "react";
import "./App.css";
import { createTeam, getTeams } from "./teamsApiClient";
import {Team} from "./Team";

function App() {
  const [teams, setTeams] = useState<Team[]>([]);
  const [teamName, setTeamName] = useState<string>("");

  const setTeamNameFromInput = (e: FormEvent<HTMLInputElement>) => {
    setTeamName(e.currentTarget.value);
  };

  const submitForm = (e: FormEvent) => {
    e.preventDefault();
    createTeam(teamName).then(() => {
      getTeams().then(setTeams);
    });
  };

  useEffect(() => {
    getTeams().then(setTeams);
  }, []);

  return (
    <>
      <ul>
        {teams.map((team, index) => (
          <li key={index}>
            {team.name}
          </li>
        ))}
      </ul>

      <form onSubmit={submitForm}>
        <label>
          Team Name
          <input
              name="team-name"
              type="text"
              onChange={setTeamNameFromInput} />
        </label>
        <button
            type="submit"
        >Submit</button>
      </form>
    </>
  );
}

export default App;
