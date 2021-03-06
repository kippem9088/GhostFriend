package GhostFriend.Server;

public class GameParams {
    public static final String DATA_DELIMITER = "/";
    public static final String COMMAND_DELIMITER = "-";
    public static final String COMMAND_DATA_DELIMITER = "_";

    // Parameters
    public static final String NO_CONTRACT = "NoContract";
    public static final String DECLARER_WIN = "DeclarerWin";
    public static final String DECLARER_LOSE = "DeclarerLose";

    // Receive Commands
    public static final String JOIN_GAME = "JoinGame";
    public static final String REPLY_DEAL_MISS = "ReplyDealMiss";
    public static final String DECLARE_CONTRACT = "DeclareContract";
    public static final String PASS_CONTRACT_DECLARATION = "PassContractDecleration";
    public static final String DISCARD_CARD = "DiscardCard";
    public static final String PASS_GIRU_CHANGE = "PassGiruChange";
    public static final String CHANGE_GIRU = "ChangeGiru";
    public static final String DETERMINE_FRIEND = "DetermineFriend";
    public static final String SUBMIT_CARD = "SubmitCard";

    // Send Commands
    public static final String PHASE_CHANGE = "PhaseChange";
    public static final String JOIN_FAIL = "JoinFail";
    public static final String JOIN_NEW_PLAYER = "JoinNewPlayer";
    public static final String EXIT_PLAYER = "ExitPlayer";
    public static final String DISTRIBUTE_CARDS = "DistributesCard";
    public static final String CHECK_DEAL_MISS = "CheckDealMiss";
    public static final String RESTART_GAME = "RestartGame";
    public static final String ASK_CONTRACT = "AskContract";
    public static final String OTHER_PLAYER_ASKING_CONTRACT = "OtherPlayerAskingContract";
    public static final String CASTER_DECLARED = "CasterDeclared";
    public static final String START_DECLARER_CARD_SELECTION = "StartDeclarerCardSelection";
    public static final String SELECT_CARDS_TO_DISCARD = "SelectCardsToDiscard";
    public static final String ASK_GIRU_CHANGE = "AskGiruChange";
    public static final String CONFIRM_CONTRACT = "ConfirmContract";
    public static final String ASK_FRIEND_CARD = "AskFriendCard";
    public static final String CONFIRM_FRIEND = "ConfirmFriend";
    public static final String NOTIFY_FRIEND = "NotifyFriend";
    public static final String START_PLAYING = "StartPlaying";
    public static final String ASK_CARD = "AskCard";
    public static final String NOTIFY_CARD_SUBMISSION = "NotifyCardSubmission";
    public static final String NOTIFY_PHASE_WINNER = "NotifyPhaseWinner";
    public static final String UPDATE_CARD_LIST = "UpdateCardList";
    public static final String NOTIFY_GAME_WINNER = "NotifyGameWinner";
}
