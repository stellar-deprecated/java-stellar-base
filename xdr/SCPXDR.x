namespace stellar
{

typedef opaque Value<>;
typedef opaque Evidence<>;

struct SCPBallot
{
    uint32 counter; // n
    Value value;    // x
};

enum SCPStatementType
{
    PREPARING = 0,
    PREPARED = 1,
    COMMITTING = 2,
    COMMITTED = 3
};

struct SCPStatement
{
    uint64 slotIndex;   // i
    SCPBallot ballot;   // b
    Hash quorumSetHash; // D

    union switch (SCPStatementType type)
    {
    case PREPARING:
        struct
        {
            SCPBallot excepted<>; // B_c
            SCPBallot* prepared;  // p
        } prepare;
    case PREPARED:
    case COMMITTING:
    case COMMITTED:
        void;
    }
    pledges;
};

struct SCPEnvelope
{
    uint256 nodeID; // v
    SCPStatement statement;
    Signature signature;
};

struct SCPQuorumSet
{
    uint32 threshold;
    Hash validators<>;
};
}
