package top.bluecraft.bcpack.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.crafting.GunSmithTableRecipe;
import com.tacz.guns.util.RenderDistance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.bluecraft.bcpack.common.init.Registration;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoadGunsScreen extends AbstractContainerScreen<LoadGunsMenu> {

    private final List<ItemStack> itemList = new ArrayList<>(); // 当前显示的物品列表
    private int currentPage = 0;  // 当前页数
    private int selectedTab = 0;  // 选择的选项卡
    private int scale = 70;

    private final List<String> recipeKeys = Lists.newArrayList();
    private final Map<String, List<ResourceLocation>> recipes = Maps.newHashMap();
    private int typePage;
    private final String selectedType;
    private final List<ResourceLocation> selectedRecipeList;
    private int indexPage;
    @Nullable
    private final GunSmithTableRecipe selectedRecipe;

    public LoadGunsScreen(LoadGunsMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.typePage = 0;
        this.selectedType = "ammo";
        this.selectedRecipeList = this.recipes.get(this.selectedType);
        this.indexPage = 0;
        this.selectedRecipe = this.getSelectedRecipe(this.selectedRecipeList.get(0));
    }

    @Nullable
    private GunSmithTableRecipe getSelectedRecipe(ResourceLocation recipeId) {
        return TimelessAPI.getAllRecipes().get(recipeId);
    }

    @Override
    protected void init() {
        // 添加分页按钮
        this.addRenderableWidget(Button.builder(Component.literal("<"), button -> {
            if (currentPage > 0) {
                currentPage--;
            }
        }).bounds(this.width / 2 - 100, this.height - 40, 20, 20).build());
        this.addRenderableWidget(Button.builder( Component.literal(">"), button -> {
            if (currentPage < getMaxPages() - 1) {
                currentPage++;
            }
        }).bounds(this.width / 2 + 80, this.height - 40, 20, 20).build());

        // 添加选项卡按钮（步枪、冲锋枪、狙击枪等）
        for (int i = 0; i < 5; i++) {
            int index = i;
            this.addRenderableWidget(Button.builder(Component.literal("Tab " + (i + 1)), button -> {
                selectedTab = index;
                currentPage = 0;  // 切换选项卡时重置到第一页
            }).bounds(10 + i * 60, 10, 50, 20).build());
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.selectedRecipe != null) {
            this.renderLeftModel(this.selectedRecipe);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

    }

    private String getItemDescription(ItemStack itemStack) {
        // 根据物品返回其描述（可以是从物品属性中获取或自定义）
        return "Some description here...";
    }

    private ItemStack getSelectedItem() {
        return null;
    }

    private int getMaxPages() {
        return (int) Math.ceil(itemList.size() / 32.0);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }


    @Override
    public Optional<GuiEventListener> getChildAt(double pMouseX, double pMouseY) {
        return super.getChildAt(pMouseX, pMouseY);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public void setFocused(boolean pFocused) {
        super.setFocused(pFocused);
    }

    @Override
    public boolean isFocused() {
        return super.isFocused();
    }

    @Override
    public @org.jetbrains.annotations.Nullable ComponentPath getCurrentFocusPath() {
        return super.getCurrentFocusPath();
    }

    @Override
    public void magicalSpecialHackyFocus(@org.jetbrains.annotations.Nullable GuiEventListener pEventListener) {
        super.magicalSpecialHackyFocus(pEventListener);
    }

    @Override
    public @org.jetbrains.annotations.Nullable ComponentPath nextFocusPath(FocusNavigationEvent pEvent) {
        return super.nextFocusPath(pEvent);
    }

    @Override
    public int getTabOrderGroup() {
        return super.getTabOrderGroup();
    }

    private void renderLeftModel(GunSmithTableRecipe recipe) {
        RenderDistance.markGuiRenderTimestamp();
        float rotationPeriod = 8.0F;
        int xPos = 60;
        int yPos = 50;
        int startX = 3;
        int startY = 16;
        int width = 128;
        int height = 99;
        float rotPitch = 15.0F;
        Window window = Minecraft.getInstance().getWindow();
        double windowGuiScale = window.getGuiScale();
        int scissorX = (int) ((double) startX * windowGuiScale);
        int scissorY = (int) ((double) window.getHeight() - (double) (startY + height) * windowGuiScale);
        int scissorW = (int) ((double) width * windowGuiScale);
        int scissorH = (int) ((double) height * windowGuiScale);
        RenderSystem.enableScissor(scissorX, scissorY, scissorW, scissorH);
        Minecraft.getInstance().textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate((float) xPos, (float) yPos, 200.0F);
        posestack.translate(8.0, 8.0, 0.0);
        posestack.scale(1.0F, -1.0F, 1.0F);
        posestack.scale((float) this.scale, (float) this.scale, (float) this.scale);
        float rot = (float) (System.currentTimeMillis() % (long) ((int) (rotationPeriod * 1000.0F))) * (360.0F / (rotationPeriod * 1000.0F));
        posestack.mulPose(Axis.XP.rotationDegrees(rotPitch));
        posestack.mulPose(Axis.YP.rotationDegrees(rot));
        RenderSystem.applyModelViewMatrix();
        PoseStack tmpPose = new PoseStack();
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        Lighting.setupForFlatItems();
        Minecraft.getInstance().getItemRenderer().renderStatic(recipe.getOutput(), ItemDisplayContext.FIXED, 15728880, OverlayTexture.NO_OVERLAY, tmpPose, bufferSource, (Level) null, 0);
        bufferSource.endBatch();
        RenderSystem.enableDepthTest();
        Lighting.setupFor3DItems();
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.disableScissor();
    }
}