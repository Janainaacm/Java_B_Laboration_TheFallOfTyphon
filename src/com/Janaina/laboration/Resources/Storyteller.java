package com.Janaina.laboration.Resources;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printYellow;
import static com.Janaina.laboration.Resources.Scanners.pressEnter;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Storyteller {

    public static void readGameLore(){
        sleepThread(YELLOW + """
                        In the ancient world of Greek mythology, the mighty Typhon, a monstrous creature with serpents for legs and fiery eyes, fell under the spell of a bewitching\s
                        beauty— a mortal maiden named Althea. With the fates conspiring, he watched her from the shadows, smitten by her grace, kindness, and ethereal charm. Her name was\s
                        whispered through the winds, and he could think of nothing else.\s
                        \s
                        In a moment of passion and recklessness, Typhon's love for Althea grew so strong that he hatched a daring plan. One moonless night, he emerged from his lair deep\s
                        within the heart of Mount Etna and, with a thunderous roar, stole Althea away under the dark shimmer of the nightsky.\s
                        The heavens themselves trembled at the audacity of Typhon's actions. The gods, watching from their celestial abode, knew that a mortal's fate hung in the balance.\s 
                        Her brother, a courageous and honorable demigod, heard of his sister's plight and knew he had to act. He was determined to save Althea from the clutches of the \s
                        monstrous Typhon and return her to safety.\s
                        \s
                        The brave hero embarked on a perilous journey to find the lair of the fearsome Typhon, a place shrouded in darkness and guarded by ancient, malevolent spirits. \s
                        Overcome with desire for revenge, he set forth to confront the colossal creature who had stolen his sister's freedom.\s
                        """ + RESET);
        yellowSuspensefulDots();
        pressEnter();
    }

    public static void pythiaBackstory(){
        printYellow("""
                Pythia, the Oracle of Delphi, has a rich and mystical background story steeped in the legends of ancient Greece.\s
                \s
                Long ago, in a realm where the gods roamed and mortal destinies were shaped, Pythia was not the revered Oracle of Delphi but rather an ordinary mortal.\s
                She was born into a humble family in the tranquil village of Delphi, nestled beneath the shadow of towering Mount Parnassus.From an early age, Pythia displayed \s
                an uncanny gift—the ability to see glimpses of the future in her dreams. Her prophetic visions were both a blessing and a burden, as they often left her in a \s
                state of trance, isolated from her peers.\s
                \s
                As she grew older, Pythia's reputation for her gift spread throughout Delphi and beyond. People traveled from distant lands to seek her counsel and guidance. \s
                Her dreams began to take on a life of their own, filled with cryptic messages and visions of gods and heroes. One fateful day, while resting by the sacred \s
                springs of Castalia, Pythia fell into a trance more profound than ever before. She saw herself ascending the steps of the Temple of Apollo, adorned in priestess \s
                robes. The god Apollo himself appeared in her vision, bestowing upon her the divine gift of prophecy. From that moment, she was chosen to be the Oracle of Delphi, \s
                the voice of the gods, and the bridge between the mortal and divine realms.She lived within the Temple of Apollo, where she inhaled the mystical fumes rising from \s
                the chasm in the temple floor. These intoxicating vapors helped her enter a state of trance, where she could channel the wisdom of the gods and provide guidance to \s
                all who sought her counsel.\s
                """);
        yellowSuspensefulDots();
        pressEnter();

    }

    public static void cerberusBackstory(){

    }
    public static void furyBackstory(){

    }
    public static void medusaBackstory(){

    }
    public static void minotaurBackstory(){

    }
    public static void sirenBackstory(){

    }
    public static void typhonBackstory(){

    }

}
