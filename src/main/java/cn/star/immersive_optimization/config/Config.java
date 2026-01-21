package cn.star.immersive_optimization.config;

import cn.star.immersive_optimization.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Config extends JsonConfig {
    private static Config INSTANCE = loadOrCreate(new Config(), Config.class);

    public Config() {
        super(Constants.MOD_ID);
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    @Override
    int getVersion() {
        return 3;
    }

    @SuppressWarnings("unused")
    public String _documentation = "https://github.com/Luke100000/ImmersiveOptimization/wiki";

    // Enable the mod. If you plan to not use it altogether, uninstall it.
    public boolean enableEntities = true;
    public boolean enableBlockEntities = true;

    // Distance culling reduces the tick rate of entities when not visible due to render distance.
    // (Singleplayer only)
    public boolean enableDistanceCulling = true;
    // Tracking culling reduces the tick rate of entities when not tracked due to distance.
    public boolean enableTrackingCulling = true;
    // Viewport culling slows down entities when outside the camera perspective.
    // (Singleplayer only)
    public boolean enableViewportCulling = true;

    // Every blocksPerLevel, the tick rate will be reduced by 1, offset by initial minDistance to avoid visible glitches.
    // Smaller values increase server performance.
    public int minDistance = 8;
    public int blocksPerLevel = 64;
    public int blocksPerLevelDistanceCulled = 10;
    public int blocksPerLevelTrackingCulled = 10;
    public int blocksPerLevelViewportCulled = 20;
    public int maxLevel = 20;

    // The same for block entities, but without further culling.
    public int blocksPerLevelBlockEntities = 32;

    public int stressedThreshold = 45;
    public float minDecreaseFactor = 0.25f;
    public boolean useEntityWhitelist = false;  // 是否启用白名单模式
    public List<String> entityWhitelist = new ArrayList<>();

    {
        entityWhitelist.add("minecraft");
    }
    
    // Set to "false" to disable scheduling on given dimensions.
    public Map<String, Boolean> dimensions;

    {
        dimensions = new HashMap<>();
        dimensions.put("minecraft:overworld", true);
        dimensions.put("minecraft:the_nether", true);
        dimensions.put("minecraft:the_end", true);
    }

    // Or entities. The blacklist accepts resource locations or namespaces (to blacklist an entire mod).
    public Map<String, Boolean> entities;

    {
        entities = new HashMap<>();

        entities.put("minecraft:player", false);
        entities.put("minecraft:ender_dragon", false);
        entities.put("minecraft:arrow", false);
        entities.put("minecraft:ender_pearl", false);

        entities.put("fromanotherworld:starship", false);

        entities.put("create", false);
        entities.put("valkyrienskies", false);
    }

    public void reload() {
        INSTANCE = loadOrCreate(new Config(), Config.class);
    }
}
