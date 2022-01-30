import {Person} from "./Person";

export type Team = {
    id: number;
    name: string;
    members: Person[];
}