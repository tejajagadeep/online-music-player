import { Image } from "./Image";
import { ExternalUrls } from "./ExternalUrls";
import { Tracks } from "./Tracks";

export class SpotifyPlaylist{
    constructor (
    public description: string,
    public externalUrls: ExternalUrls,
    public id: string,
    public images: Image[],
    public name: string,
    public tracks: Tracks
    ) {
    }
}