import { Album } from './Album';
import { Artist } from './Artist';
import { ExternalUrls } from './ExternalUrls';

export class Track{
    constructor(
        public album: Album,
        public artists: Artist[],
        public ExternalUrls: ExternalUrls,
        public id: string,
        public name: string,
        public previewUrl: string,
        public type: string
    ) {}
}