-- DROP TABLE IF EXISTS bets;

create table bets
(
    bet_id          uuid not null primary key,
    bet_amount      numeric(38, 2),
    event_id        uuid,
    event_market_id uuid,
    event_winner_id uuid,
    user_id         uuid,
    is_settled      boolean default false
);


CREATE INDEX idx_bets_event_id_winner_id
    ON bets (event_id, event_winner_id);
