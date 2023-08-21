package dev.jdevv.dao;

import dev.jdevv.model.Wishlist;
import dev.jdevv.model.WishlistItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWishlistDao implements WishlistDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWishlistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Wishlist getWishlistById(int wishlistId, int userId) {
        String sql = "SELECT w.*, wi.wishlist_item_id, wi.product_id " +
                "FROM wishlist w " +
                "LEFT JOIN wishlist_item wi " +
                "ON w.wishlist_id = wi.wishlist_id " +
                "WHERE w.wishlist_id = ? " +
                "AND w.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wishlistId, userId);
        Wishlist wishlist = null;
        while (results.next()) {
            if (wishlist == null) {
                wishlist = mapRowToWishlist(results);
                wishlist.setItems(new ArrayList<>());
            }

            WishlistItem item = mapRowToWishlistItem(results);
            if (item != null) {
                wishlist.getItems().add(item);
            }
        }
        return wishlist;
    }

    @Override
    public List<Wishlist> getWishlists(int userId) {
        List<Wishlist> wishlists = new ArrayList<>();
        String sql = "SELECT * FROM wishlist WHERE user_id = ? ORDER BY wishlist_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Wishlist wishlist = mapRowToWishlist(results);
            wishlists.add(wishlist);
        }
        return wishlists;
    }

    @Override
    public int addWishlistItem(WishlistItem wishlistItem) {
        String sql = "INSERT INTO wishlist_item (wishlist_id, product_id) VALUES (?, ?) RETURNING wishlist_item_id;";
        int wishlistId = jdbcTemplate.queryForObject(sql, int.class, wishlistItem.getWishlistId(), wishlistItem.getProductId());
        return wishlistId;
    }

    @Override
    public void removeWishlistItem(int wishlistId, int userId, int productId) {

    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return null;
    }

    @Override
    public void removeWishlist(int wishlistId, int userId) {

    }


    private Wishlist mapRowToWishlist(SqlRowSet results) {

        Wishlist wishlist = new Wishlist();

        wishlist.setWishlistId(results.getInt("wishlist_id"));
        wishlist.setUserId(results.getInt("user_id"));
        wishlist.setName(results.getString("name"));

        return wishlist;
    }

    public WishlistItem mapRowToWishlistItem (SqlRowSet results) {
        WishlistItem item = new WishlistItem();

        item.setWishlistItemId(results.getInt("wishlist_item_id"));
        item.setWishlistId(results.getInt("wishlist_id"));
        item.setProductId(results.getInt("product_id"));

        return (item.getWishlistItemId() == 0 ? null : item);
    }
}

