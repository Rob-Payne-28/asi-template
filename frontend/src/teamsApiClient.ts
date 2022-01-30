import axios from "axios";
import {Team} from "./Team";
import {Person} from "./Person";

export async function createTeam(teamName: string): Promise<Team> {
  return (await axios.post("/team", teamName, {headers: {'Content-Type': 'application/json'}})).data
}

export async function createPerson(personName: string): Promise<Person> {
  return (await axios.post("/person", personName, {headers: {'Content-Type': 'application/json'}})).data
}

export async function getTeams(): Promise<Team[]> {
  return (await axios.get<Team[]>("/teams")).data
}
