import { ExternalUrls } from "./ExternalUrls";

export class Artist{
    constructor(
        public ExternalUrls: ExternalUrls,
        public id: string,
        public name: string,
        public type: string
    ) { }
}