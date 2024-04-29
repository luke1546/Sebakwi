export const PALETTE = {
  MAIN_BLUE: '#0052A3',
  SUB_BLUE : '#E5EDF5',
  LIGHT_BLUE: '#F2F6FA',
  MAIN_BLACK: '#1A1A1A',
  LIGHT_BLACK: '#BABABA',
  LIGHT_RED: '#FFE8E4',
} as {
  [key: string]: string;
  MAIN_BLUE: '#0052A3';
  SUB_BLUE : '#E5EDF5',
  LIGHT_BLUE: '#F2F6FA';
  MAIN_BLACK: '#1A1A1A';
  LIGHT_BLACK: '#BABABA';
  LIGHT_RED: '#FFE8E4';
};

type PaletteType = typeof PALETTE;
type ValueOfPalette<PaletteType> = PaletteType[keyof PaletteType];

export type { PaletteType, ValueOfPalette };
