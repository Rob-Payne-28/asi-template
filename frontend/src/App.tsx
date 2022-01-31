import React, {FormEvent, useEffect, useState} from "react";
import "./App.css";
import {createPerson, createTeam, getTeams} from "./teamsApiClient";
import {Team} from "./Team";

function App() {
    const [teams, setTeams] = useState<Team[]>([]);
    const [teamName, setTeamName] = useState<string>("");
    const [personName, setPersonName] = useState<string>("");

    const setTeamNameFromInput = (e: FormEvent<HTMLInputElement>) => {
        setTeamName(e.currentTarget.value);
    };

    const setPersonNameFromInput = (e: FormEvent<HTMLInputElement>) => {
        setPersonName(e.currentTarget.value);
    };

    const submitTeamCreationForm = (e: FormEvent) => {
        e.preventDefault();
        createTeam(teamName).then(() => {
            getTeams().then(setTeams);
        });
    };

    const submitPersonCreationForm = (e: FormEvent) => {
        e.preventDefault();
        createPerson(personName).then(() => {
            getTeams().then(setTeams);
        });
    };

    useEffect(() => {
        getTeams().then(setTeams);
    }, []);

    return (
        <>
            <ul>
                {teams.map(({name, members}, index) => {
                    return (
                        <li key={index}>
                            {name}
                            <ul>
                                {members.map(({name}, i) => {
                                    return (
                                        <li key={i}>
                                            {name}
                                        </li>)
                                })}
                            </ul>
                        </li>
                    )
                })}
            </ul>

            <form onSubmit={submitTeamCreationForm}>
                <label>
                    Team Name
                    <input
                        name="team-name"
                        type="text"
                        onChange={setTeamNameFromInput}/>
                </label>
                <button
                    type="submit"
                >Create Team
                </button>
            </form>

            <form onSubmit={submitPersonCreationForm}>
                <label>
                    Person Name
                    <input
                        name="person-name"
                        type="text"
                        onChange={setPersonNameFromInput}/>
                </label>
                <button
                    type="submit"
                >Create Person
                </button>
            </form>
        </>
    );
}

export default App;
