package Pinball;
/*********************************************************************************/
/**
 *   Score is control of manipulating and resetting  the int reprsentation of the
 *   of the score being displaying on the Game
 */
/*********************************************************************************/
class Score extends Display
{

    private int score = 0;

    public Score()
    {

    }

    /**
     *
     * @param reset the score value
     */
    public void Reset()
    {
        score = 0;
    }

    /**
     *
     * @return the current score
     */
    public int getCurrentValue()
    {
        return score;
    }

    /**
     *
     * @param value  the score incremented by the current value
     *               currently not being used.
     */
    public void incrementBy(int value)
    {
       score = score + value;
    }
}
/*********************************************************************************/
/*********************************************************************************/