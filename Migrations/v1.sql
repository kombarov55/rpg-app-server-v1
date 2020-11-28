insert into game_settings (id, inventory_size, is_char_img_uploadable, game_id, disclaimer_text)
  select random_uuid(), 0, false, T.id, '' from game T